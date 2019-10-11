package com.spirit.community.srpc.core.roomgate;

import android.util.Log;

import com.spirit.community.common.RpcEventType;
import com.spirit.community.protocol.thrift.common.CommonRes;
import com.spirit.community.protocol.thrift.common.HelloNotify;
import com.spirit.community.protocol.thrift.login.ClientLoginRes;
import com.spirit.community.protocol.thrift.login.UserRegisterRes;
import com.spirit.community.srpc.core.Encrypt;
import com.spirit.community.srpc.core.SRpcBizApp;
import com.spirit.community.srpc.core.State;
import com.spirit.tba.Exception.TbaException;
import com.spirit.tba.core.EncryptType;
import com.spirit.tba.core.TbaAes;
import com.spirit.tba.core.TbaEvent;
import com.spirit.tba.core.TsHeadMagic;
import com.spirit.tba.core.TsRpcByteBuffer;
import com.spirit.tba.core.TsRpcEventParser;
import com.spirit.tba.core.TsRpcHead;
import com.spirit.tba.core.TsRpcProtocolFactory;

import java.util.List;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.ByteToMessageDecoder;

public class RoomGateProtocolDecoder extends ByteToMessageDecoder {

    @Override
    protected void decode(ChannelHandlerContext ctx, ByteBuf in, List<Object> out) throws Exception {
        // TODO Auto-generated method stub

        while (in.readableBytes() > 4) {
            
        	//in.markReaderIndex();

            int msg_len = in.readInt();
            short flag = in.readShort();

            TsRpcByteBuffer msg = null;

            if(flag == EncryptType.WHOLE) {
                byte[] encrypt = new byte[msg_len - TsHeadMagic.MAGIC_OFFSET];
                for (int i = 0; i < msg_len - TsHeadMagic.MAGIC_OFFSET; i++) {
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
            }
            else {
                msg = new TsRpcByteBuffer(msg_len);
                msg.WriteI32(msg_len);
                msg.WriteI16(flag);
                for (int i = 0; i < msg_len - TsHeadMagic.MAGIC_OFFSET; i++) {
                    msg.WriteByte(in.readByte());
                }
            }

            TsRpcEventParser parser = new TsRpcEventParser(msg);
            TsRpcHead header = parser.Head();

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
