package com.spirit.community.srpc.core;

import android.util.Log;

import java.util.List;

import com.spirit.community.common.RpcEventType;
import com.spirit.community.protocol.thrift.common.HelloNotify;
import com.spirit.community.protocol.thrift.login.ClientLoginRes;
import com.spirit.community.protocol.thrift.login.UserRegisterRes;
import com.spirit.tba.Exception.TbaException;
import com.spirit.tba.core.TbaAes;
import com.spirit.tba.core.TbaEvent;
import com.spirit.tba.core.TbaMd5;
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

            if(flag == Encrypt.TYPE_ENABLE) {
                byte[] encrypt = new byte[msg_len - 4];
                for (int i = 0; i < msg_len - 4; i++) {
                    encrypt[i] = in.readByte();
                }
//                String str = new String(encrypt, "utf-8");
//                System.out.println("encrypt str =============>" + str);
//                System.out.println("len:" + str.length());

                String key = "123";
                //String original = TbaAes.decrypt(new String(encrypt, "utf-8"), "123");
                String original = TbaAes.decode(new String(encrypt, "utf-8"), key);
                byte[] msg00 = original.getBytes("ISO8859-1");
                msg = new TsRpcByteBuffer(msg00, msg00.length);
            }
            else {
                msg = new TsRpcByteBuffer(msg_len);
                msg.WriteI32(msg_len);
                msg.WriteI16(flag);
                for (int i = 0; i < msg_len - 6; i++) {
                    msg.WriteByte(in.readByte());
                }
            }


            TsRpcEventParser parser = new TsRpcEventParser(msg);
            TsRpcHead header = parser.Head();

            try {
                switch (header.GetType()) {

                    case RpcEventType.MT_HELLO_NOTIFY: {
                        TsRpcProtocolFactory<HelloNotify> protocol = new TsRpcProtocolFactory<HelloNotify>(msg);
                        out.add(new TbaEvent(header, protocol.Decode(HelloNotify.class)));
                    }
                    break;

                    case RpcEventType.MT_CLIENT_LOGIN_RES: {
                        TsRpcProtocolFactory<ClientLoginRes> protocol = new TsRpcProtocolFactory<ClientLoginRes>(msg);
                        ClientLoginRes res = protocol.Decode(ClientLoginRes.class);
                        out.add(new TbaEvent(header, res));
                    }
                    break;

                    case RpcEventType.MT_CLIENT_REGISTER_RES: {
                        TsRpcProtocolFactory<UserRegisterRes> protocol = new TsRpcProtocolFactory<UserRegisterRes>(msg);
                        out.add(new TbaEvent(header, protocol.Decode(UserRegisterRes.class)));
                    }
                    break;

                    default:
                        break;
                }
            }
            catch(TbaException e){
                //log.error(e.getLocalizedMessage(), e);
            }
            catch(InstantiationException e){
                //log.error(e.getLocalizedMessage(), e);
            }
            catch(IllegalAccessException e){
                //log.error(e.getLocalizedMessage(), e);
            }



        }
        
      

        
    }

}
