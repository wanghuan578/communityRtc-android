package com.spirit.community.srpc.core.roomgate;

import android.util.Log;

import com.spirit.community.common.RpcEventType;
import com.spirit.community.protocol.thrift.common.CommonRes;
import com.spirit.community.protocol.thrift.common.HelloNotify;
import com.spirit.community.protocol.thrift.login.ClientLoginRes;
import com.spirit.community.protocol.thrift.login.UserRegisterRes;
import com.spirit.community.protocol.thrift.roomgate.ChatReq;
import com.spirit.community.srpc.core.Encrypt;
import com.spirit.community.srpc.core.SRpcBizApp;
import com.spirit.community.srpc.core.State;
import com.spirit.tba.Exception.TbaException;
import com.spirit.tba.core.EncryptType;
import com.spirit.tba.core.TbaAes;
import com.spirit.tba.core.TbaEvent;
import com.spirit.tba.core.TsMagic;
import com.spirit.tba.core.TsRpcByteBuffer;
import com.spirit.tba.core.TsRpcEventParser;
import com.spirit.tba.core.TsRpcHead;
import com.spirit.tba.core.TsRpcProtocolFactory;
import com.spirit.tba.tools.TbaHeadUtil;
import com.spirit.tba.tools.TbaToolsKit;

import java.util.List;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.ByteToMessageDecoder;

public class RoomGateProtocolDecoder extends ByteToMessageDecoder {

    @Override
    protected void decode(ChannelHandlerContext ctx, ByteBuf in, List<Object> out) throws Exception {
        // TODO Auto-generated method stub

        while (in.readableBytes() > 4) {

            byte [] magic = new byte[TsMagic.MAGIC_OFFSET];
            for (int i = 0; i < TsMagic.MAGIC_OFFSET; i++) {
                magic[i] = in.readByte();
            }
            TsMagic magicHead = TbaHeadUtil.preParser(magic);

            int msg_len = magicHead.getLength();
            short flag = magicHead.getFlag();

            TsRpcByteBuffer msg = null;
            TsRpcHead header = null;

            if(flag == EncryptType.WHOLE) {
                byte[] encrypt = new byte[msg_len - TsMagic.MAGIC_OFFSET];
                for (int i = 0; i < msg_len - TsMagic.MAGIC_OFFSET; i++) {
                    encrypt[i] = in.readByte();
                }

                Long key = SRpcBizApp.getInstance().getRoomGate().getServerRandom();

                Log.i(this.toString(),"decrypt key: " + key);
                String original = TbaAes.decode(new String(encrypt, "utf-8"), String.valueOf(key));
                byte[] msg00 = original.getBytes("ISO8859-1");
                msg = new TsRpcByteBuffer(msg00, msg00.length);
            }
            else if(flag == EncryptType.BODY) {
                Log.i(this.toString(),"chat notify");
                byte[] all = new byte[TbaHeadUtil.HEAD_SIZE];
                System.arraycopy(magic, 0 , all, 0, TsMagic.MAGIC_OFFSET);
                for (int i = TsMagic.MAGIC_OFFSET; i < TbaHeadUtil.HEAD_SIZE; i++) {
                    all[i] = in.readByte();
                }

                header = TbaHeadUtil.parser(all);
                if (header.GetType() == RpcEventType.ROOMGATE_CHAT_NOTIFY) {

                    byte[] encryptData = new byte[msg_len - TbaHeadUtil.HEAD_SIZE];
                    for (int i = 0; i < msg_len - TbaHeadUtil.HEAD_SIZE; i++) {
                        encryptData[i] = in.readByte();
                    }
                    Long key = TbaToolsKit.int2long(new int[] {header.GetAttach1(), header.GetAttach2()});
                    Log.i(this.toString(),"chat decrypt key: " + key);
                    //Long serverRandom = SRpcBizApp.getInstance().getRoomGate().getServerRandom();
                    String original = TbaAes.decode(new String(encryptData, "utf-8"), String.valueOf(key));
                    ChatReq req = new TbaToolsKit<ChatReq>().deserialize(original.getBytes("ISO8859-1"), ChatReq.class);
                    out.add(new TbaEvent(header, req));
                    return;
                }
            }
            else {
                msg = new TsRpcByteBuffer(msg_len);
                msg.WriteI32(msg_len);
                msg.WriteI16(flag);
                for (int i = 0; i < msg_len - TsMagic.MAGIC_OFFSET; i++) {
                    msg.WriteByte(in.readByte());
                }
            }

            TsRpcEventParser parser = new TsRpcEventParser(msg);
            header = parser.Head();

            try {
                if (header.GetType() == RpcEventType.MT_HELLO_NOTIFY) {
                    TsRpcProtocolFactory<HelloNotify> protocol = new TsRpcProtocolFactory<HelloNotify>(msg);
                    HelloNotify notify = protocol.Decode(HelloNotify.class);
                    SRpcBizApp.getInstance().getRoomGate().setServerRandom(notify.getServer_random());
                    out.add(new TbaEvent(header, notify));
                }
                else if (header.GetType() ==  RpcEventType.ROOMGATE_CONNECT_RES) {
                    TsRpcProtocolFactory<CommonRes> protocol = new TsRpcProtocolFactory<CommonRes>(msg);
                    CommonRes res = protocol.Decode(CommonRes.class);
                    out.add(new TbaEvent(header, res));
                }
            }
            catch(TbaException e){
                Log.e(this.toString(), e.getMessage());
            }
            catch(InstantiationException e){
                Log.e(this.toString(), e.getMessage());
            }
            catch(IllegalAccessException e){
                Log.e(this.toString(), e.getMessage());
            }



        }
        
      

        
    }

}
