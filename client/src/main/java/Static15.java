import org.openrs2.deob.annotation.OriginalArg;
import org.openrs2.deob.annotation.OriginalMember;
import org.openrs2.deob.annotation.Pc;

public final class Static15 {

	@OriginalMember(owner = "client!be", name = "Vb", descriptor = "F")
	public static float aFloat4;

	@OriginalMember(owner = "client!be", name = "ib", descriptor = "Lclient!na;")
	public static final JagString EVENT_OPBASE = JagString.parse("event_opbase");

	@OriginalMember(owner = "client!be", name = "ac", descriptor = "Lclient!na;")
	public static JagString aClass100_87 = null;

	@OriginalMember(owner = "client!be", name = "Ec", descriptor = "I")
	public static int anInt506 = -1;

	@OriginalMember(owner = "client!be", name = "a", descriptor = "(Z)Lclient!na;")
	public static JagString method479() {
		@Pc(8) JagString local8 = Static93.aClass100_518;
		@Pc(10) JagString local10 = JagString.EMPTY;
		if (client.modeWhere != 0) {
			local8 = Static50.aClass100_365;
		}
		if (client.settings != null) {
			local10 = JagString.concatenate(new JagString[] { Static150.aClass100_687, client.settings});
		}
		return JagString.concatenate(new JagString[] { Static61.aClass100_424, local8, Static80.aClass100_886, Static123.parseInt(client.language), Static257.aClass100_98, Static123.parseInt(client.affiliate), local10, Static41.aClass100_268 });
	}

	@OriginalMember(owner = "client!be", name = "a", descriptor = "(III)I")
	public static int method484(@OriginalArg(0) int arg0, @OriginalArg(2) int arg1) {
		@Pc(10) Inv local10 = (Inv) Static20.objectContainerCache.get((long) arg0);
		if (local10 == null) {
			return -1;
		} else if (arg1 >= 0 && arg1 < local10.objectIds.length) {
			return local10.objectIds[arg1];
		} else {
			return -1;
		}
	}

	@OriginalMember(owner = "client!be", name = "a", descriptor = "(II)I")
	public static int method492(@OriginalArg(1) int arg0) {
		return arg0 == 16711935 ? -1 : Static105.method2253(arg0);
	}
}
