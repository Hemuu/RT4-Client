import org.openrs2.deob.annotation.OriginalArg;
import org.openrs2.deob.annotation.OriginalMember;
import org.openrs2.deob.annotation.Pc;

public final class Static154 {

    @OriginalMember(owner = "client!md", name = "S", descriptor = "I")
	public static int anInt3709;

	@OriginalMember(owner = "client!md", name = "Q", descriptor = "Lclient!na;")
	public static final JagString aClass100_735 = JagString.parse("::mm");

	@OriginalMember(owner = "client!md", name = "T", descriptor = "Lclient!na;")
	public static final JagString aClass100_736 = JagString.parse("Shift)2click disabled)3");

    @OriginalMember(owner = "client!md", name = "a", descriptor = "(Lclient!be;I)Z")
	public static boolean method2926(@OriginalArg(0) Component arg0) {
		if (arg0.anIntArray43 == null) {
			return false;
		}
		for (@Pc(14) int local14 = 0; local14 < arg0.anIntArray43.length; local14++) {
			@Pc(34) int local34 = Static273.method3212(local14, arg0);
			@Pc(39) int local39 = arg0.anIntArray48[local14];
			if (arg0.anIntArray43[local14] == 2) {
				if (local39 <= local34) {
					return false;
				}
			} else if (arg0.anIntArray43[local14] == 3) {
				if (local34 <= local39) {
					return false;
				}
			} else if (arg0.anIntArray43[local14] == 4) {
				if (local34 == local39) {
					return false;
				}
			} else if (local39 != local34) {
				return false;
			}
		}
		return true;
	}

	@OriginalMember(owner = "client!md", name = "a", descriptor = "(IILclient!na;Lclient!na;BLclient!na;)V")
	public static void method2928(@OriginalArg(0) int arg0, @OriginalArg(1) int arg1, @OriginalArg(2) JagString arg2, @OriginalArg(3) JagString arg3, @OriginalArg(5) JagString arg4) {
		for (@Pc(14) int local14 = 99; local14 > 0; local14--) {
			Static26.anIntArray67[local14] = Static26.anIntArray67[local14 - 1];
			Static153.aClass100Array112[local14] = Static153.aClass100Array112[local14 - 1];
			Static230.aClass100Array158[local14] = Static230.aClass100Array158[local14 - 1];
			Static64.aClass100Array62[local14] = Static64.aClass100Array62[local14 - 1];
			Static241.anIntArray521[local14] = Static241.anIntArray521[local14 - 1];
		}
		Static62.anInt1941++;
		Static26.anIntArray67[0] = arg1;
		Static153.aClass100Array112[0] = arg4;
		Static49.anInt1464 = InterfaceList.transmitTimer;
		Static241.anIntArray521[0] = arg0;
		Static230.aClass100Array158[0] = arg2;
		Static64.aClass100Array62[0] = arg3;
	}

	@OriginalMember(owner = "client!md", name = "a", descriptor = "(JB)Lclient!na;")
	public static JagString method2929(@OriginalArg(0) long arg0) {
		return Static59.method1376(arg0);
	}
}
