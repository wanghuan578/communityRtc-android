package com.spirit.community.srpc.core.loginserver;

import android.util.Log;
import com.spirit.community.srpc.core.SRpcBizApp;
import com.spirit.community.srpc.core.State;
import com.spirit.tba.core.EncryptType;
import com.spirit.tba.core.TbaAes;
import com.spirit.tba.core.TbaEvent;
import com.spirit.tba.core.TsMagic;
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

		if (ev.getEncryptType() == EncryptType.WHOLE) {
			TsRpcHead head = ev.getHead();
			TsRpcProtocolFactory protocol = new TsRpcProtocolFactory<TBase>((TBase)ev.getBody(), head, ev.getLength());
			byte[] buf = protocol.Encode().OutStream().GetBytes();

			Long key = null;
			if (SRpcBizApp.getInstance().getState() == State.LOGIN_SERVER_LOGIN) {
				key = SRpcBizApp.getInstance().getLoginServer().getServerRandom();
			}

			Log.i(this.toString(),"encrypt key: " + key);
			String encrypt = TbaAes.encode(new String(buf, "ISO8859-1"), String.valueOf(key));

			TsRpcByteBuffer byteBuff = new TsRpcByteBuffer(encrypt.length() + TsMagic.MAGIC_OFFSET);
			byteBuff.WriteI32(encrypt.length() + TsMagic.MAGIC_OFFSET);
			byteBuff.WriteI16(ev.getEncryptType());
			byteBuff.copy(encrypt.getBytes());
			byte [] o = byteBuff.GetBytes();
			Log.i(this.toString(),"encrypt msg len: " + o.length);
			out.writeBytes(o, 0, o.length);
		}
		else {
			TsRpcHead head = ev.getHead();
			TsRpcProtocolFactory protocol = new TsRpcProtocolFactory<TBase>((TBase)ev.getBody(), head, ev.getLength());
			byte[] buf = protocol.Encode().OutStream().GetBytes();
			out.writeBytes(buf, 0, buf.length);
		}
	}

}
