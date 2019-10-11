package com.spirit.community.srpc.core.loginserver;

import android.util.Log;
import java.util.List;
import com.spirit.community.common.RpcEventType;
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
import com.spirit.tba.core.TsMagic;
import com.spirit.tba.core.TsRpcByteBuffer;
import com.spirit.tba.core.TsRpcEventParser;
import com.spirit.tba.core.TsRpcHead;
import com.spirit.tba.core.TsRpcProtocolFactory;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.ByteToMessageDecoder;

public class TbaProtocolDecoder extends ByteToMessageDecoder {

    @Override
    protected void decode(ChannelHandlerContext ctx, ByteBuf in, List<Object> out) throws Exception {
        // TODO Auto-generated method stub

        while (in.readableBytes() > 4) {
            
        	//in.markReaderIndex();

            int msg_len = in.readInt();
            short flag = in.readShort();

            TsRpcByteBuffer msg = null;

            if(flag == EncryptType.WHOLE) {
                byte[] encrypt = new byte[msg_len - TsMagic.MAGIC_OFFSET];
                for (int i = 0; i < msg_len - TsMagic.MAGIC_OFFSET; i++) {
                    encrypt[i] = in.readByte();
                }

                Long key = null;
                if (SRpcBizApp.getInstance().getState() == State.LOGIN_SERVER_LOGIN) {
                    key = SRpcBizApp.getInstance().getLoginServer().getServerRandom();
                }
                Log.i(this.toString(),"decrypt key: " + key);
                String original = TbaAes.decode(new String(encrypt, "utf-8"), String.valueOf(key));
                byte[] msg00 = original.getBytes("ISO8859-1");
                msg = new TsRpcByteBuffer(msg00, msg00.length);
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
            TsRpcHead header = parser.Head();

            try {
                if (header.GetType() == RpcEventType.MT_HELLO_NOTIFY) {
                    TsRpcProtocolFactory<HelloNotify> protocol = new TsRpcProtocolFactory<HelloNotify>(msg);
                    HelloNotify notify = protocol.Decode(HelloNotify.class);
                    SRpcBizApp.getInstance().getLoginServer().setServerRandom(notify.getServer_random());
                    out.add(new TbaEvent(header, notify));
                }
                else if (header.GetType() ==  RpcEventType.MT_CLIENT_LOGIN_RES) {
                    TsRpcProtocolFactory<ClientLoginRes> protocol = new TsRpcProtocolFactory<ClientLoginRes>(msg);
                    ClientLoginRes res = protocol.Decode(ClientLoginRes.class);
                    out.add(new TbaEvent(header, res));
                }
                else if (header.GetType() ==  RpcEventType.MT_CLIENT_REGISTER_RES) {
                    TsRpcProtocolFactory<UserRegisterRes> protocol = new TsRpcProtocolFactory<UserRegisterRes>(msg);
                    out.add(new TbaEvent(header, protocol.Decode(UserRegisterRes.class)));
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
