import org.openrs2.deob.annotation.OriginalArg;
import org.openrs2.deob.annotation.OriginalMember;
import org.openrs2.deob.annotation.Pc;

public final class Static225 {

	@OriginalMember(owner = "client!se", name = "a", descriptor = "I")
	public static int originX;

    @OriginalMember(owner = "client!se", name = "h", descriptor = "I")
	public static int anInt5073 = -1;

	@OriginalMember(owner = "client!se", name = "m", descriptor = "Lclient!na;")
	public static final JagString aClass100_961 = JagString.parse(" )2>");

	@OriginalMember(owner = "client!se", name = "a", descriptor = "(I)V")
	public static void method3889() {
		while (true) {
			if (Protocol.inboundBuffer.method2241(Protocol.length) >= 11) {
				@Pc(20) int local20 = Protocol.inboundBuffer.method2238(11);
				if (local20 != 2047) {
					@Pc(27) boolean local27 = false;
					if (Static159.players[local20] == null) {
						Static159.players[local20] = new Player();
						local27 = true;
						if (Static115.aClass3_Sub15Array1[local20] != null) {
							Static159.players[local20].method1262(Static115.aClass3_Sub15Array1[local20]);
						}
					}
					Static105.anIntArray256[Static267.anInt5774++] = local20;
					@Pc(65) Player local65 = Static159.players[local20];
					local65.anInt3430 = Static83.loop;
					@Pc(73) int local73 = Protocol.inboundBuffer.method2238(1);
					if (local73 == 1) {
						Static44.anIntArray106[Static116.anInt2951++] = local20;
					}
					@Pc(92) int local92 = Protocol.inboundBuffer.method2238(5);
					@Pc(99) int local99 = Static56.anIntArray141[Protocol.inboundBuffer.method2238(3)];
					if (local92 > 15) {
						local92 -= 32;
					}
					if (local27) {
						local65.anInt3400 = local65.anInt3381 = local99;
					}
					@Pc(116) int local116 = Protocol.inboundBuffer.method2238(1);
					@Pc(121) int local121 = Protocol.inboundBuffer.method2238(5);
					if (local121 > 15) {
						local121 -= 32;
					}
					local65.method1265(local92 + PlayerList.self.movementQueueX[0], local116 == 1, PlayerList.self.movementQueueZ[0] + local121);
					continue;
				}
			}
			Protocol.inboundBuffer.method2244();
			return;
		}
	}

	@OriginalMember(owner = "client!se", name = "a", descriptor = "(IIIIZ)I")
	public static int method3891(@OriginalArg(0) int arg0, @OriginalArg(1) int arg1, @OriginalArg(2) int arg2, @OriginalArg(3) int arg3) {
		@Pc(8) int local8 = arg3 & 0xF;
		@Pc(29) int local29 = local8 >= 4 ? (local8 == 12 || local8 == 14 ? arg0 : arg1) : arg2;
		@Pc(42) int local42 = local8 < 8 ? arg0 : arg2;
		return ((local8 & 0x1) == 0 ? local42 : -local42) + ((local8 & 0x2) == 0 ? local29 : -local29);
	}

	@OriginalMember(owner = "client!se", name = "a", descriptor = "(III)V")
	public static void method3893(@OriginalArg(0) int arg0, @OriginalArg(1) int arg1) {
		@Pc(14) DelayedStateChange local14 = Static238.method4143(5, arg0);
		local14.pushServer();
		local14.intArg1 = arg1;
	}

	@OriginalMember(owner = "client!se", name = "a", descriptor = "(Lclient!na;Lclient!na;IB)V")
	public static void method3896(@OriginalArg(0) JagString arg0, @OriginalArg(1) JagString arg1, @OriginalArg(2) int arg2) {
		Player.password = arg1;
		LoginManager.anInt39 = arg2;
		Player.username = arg0;
		if (Player.username.strEquals(JagString.EMPTY) || Player.password.strEquals(JagString.EMPTY)) {
			LoginManager.reply = 3;
		} else if (Static125.worldId == -1) {
			LoginManager.anInt673 = 0;
			LoginManager.anInt4587 = 0;
			LoginManager.reply = -3;
			LoginManager.anInt4937 = 1;
			@Pc(43) Buffer local43 = new Buffer(128);
			local43.p1(10);
			local43.p2((int) (Math.random() * 99999.0D));
			local43.p2(530);
			local43.p8(Player.username.encode37());
			local43.p4((int) (Math.random() * 9.9999999E7D));
			local43.pjstr(Player.password);
			local43.p4((int) (Math.random() * 9.9999999E7D));
			local43.encryptRsa(GlobalConfig.RSA_EXPONENT, GlobalConfig.RSA_MODULUS);
			Protocol.outboundBuffer.offset = 0;
			Protocol.outboundBuffer.p1(210);
			Protocol.outboundBuffer.p1(local43.offset);
			Protocol.outboundBuffer.pBytes(local43.data, local43.offset);
		} else {
			LoginManager.clear();
		}
	}
}
