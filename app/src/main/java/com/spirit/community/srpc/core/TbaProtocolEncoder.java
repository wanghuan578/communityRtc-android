package com.spirit.community.srpc.core;

import android.util.Log;
import com.spirit.tba.Exception.TbaException;
import com.spirit.tba.core.TbaAes;
import com.spirit.tba.core.TbaEvent;
import com.spirit.tba.core.TsRpcByteBuffer;
import com.spirit.tba.core.TsRpcHead;
import com.spirit.tba.core.TsRpcProtocolFactory;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToByteEncoder;
import org.apache.thrift.TBase;

public class TbaProtocolEncoder extends MessageToByteEncoder<Object> {

	@Override
	protected void encode(ChannelHandlerContext ctx, Object msg, ByteBuf out) throws Exception {

		TbaEvent ev = (TbaEvent) msg;

		try {
			if (ev.isEncrypt()) {
				TsRpcHead head = ev.getHead();
				TsRpcProtocolFactory protocol = new TsRpcProtocolFactory<TBase>((TBase)ev.getBody(), head, ev.getLength());
				byte[] buf = protocol.Encode().OutStream().GetBytes();

				String key = "123";
				String encrypt = TbaAes.encode(new String(buf, "ISO8859-1"), key);

				TsRpcByteBuffer byteBuff = new TsRpcByteBuffer(encrypt.length() + 6);
				byteBuff.WriteI32(encrypt.length() + 4);
				byteBuff.WriteI16((short)1);
				byteBuff.copy(encrypt.getBytes());
				byte [] o = byteBuff.GetBytes();
				Log.i(this.toString(),"encrypt o len: " + o.length);
				out.writeBytes(o, 0, o.length);
			}
			else {
				TsRpcHead head = ev.getHead();
				TsRpcProtocolFactory protocol = new TsRpcProtocolFactory<TBase>((TBase)ev.getBody(), head, ev.getLength());
				byte[] buf = protocol.Encode().OutStream().GetBytes();
				out.writeBytes(buf, 0, buf.length);
			}
		}
		catch (TbaException e) {
			//log.error(e.getLocalizedMessage(), e);
		}
	}

}
