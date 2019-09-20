package com.spirit.community.srpc.core;

import java.util.List;

import com.spirit.community.common.RpcEventType;
import com.spirit.community.protocol.thrift.ClientLoginRes;
import com.spirit.community.protocol.thrift.HelloNotify;
import com.spirit.community.protocol.thrift.UserRegisterRes;
import com.spirit.tba.Exception.TbaException;
import com.spirit.tba.core.TsEvent;
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
            TsRpcByteBuffer msg = new TsRpcByteBuffer(msg_len);
            msg.WriteI32(msg_len);

            for (int i = 0; i < msg_len - 4; i++) {
                msg.WriteByte(in.readByte());
            }

            TsRpcEventParser parser = new TsRpcEventParser(msg);
            TsRpcHead header = parser.Head();

            //log.info("Msg Type: {}", header.GetType());

            try {
                switch (header.GetType()) {

                    case RpcEventType.MT_HELLO_NOTIFY: {
                        TsRpcProtocolFactory<HelloNotify> protocol = new TsRpcProtocolFactory<HelloNotify>(msg);
                        out.add(new TsEvent(header, protocol.Decode(HelloNotify.class)));
                    }
                    break;

                    case RpcEventType.MT_CLIENT_LOGIN_RES: {
                        TsRpcProtocolFactory<ClientLoginRes> protocol = new TsRpcProtocolFactory<ClientLoginRes>(msg);
                        out.add(new TsEvent(header, protocol.Decode(ClientLoginRes.class)));
                    }
                    break;

                    case RpcEventType.MT_CLIENT_REGISTER_RES: {
                        TsRpcProtocolFactory<UserRegisterRes> protocol = new TsRpcProtocolFactory<UserRegisterRes>(msg);
                        out.add(new TsEvent(header, protocol.Decode(UserRegisterRes.class)));
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
