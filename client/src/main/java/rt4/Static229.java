package rt4;

import org.openrs2.deob.annotation.OriginalArg;
import org.openrs2.deob.annotation.OriginalMember;

public final class Static229 {

	@OriginalMember(owner = "client!si", name = "ab", descriptor = "I")
	public static int anInt5138;

    @OriginalMember(owner = "client!si", name = "gb", descriptor = "Lclient!na;")
	public static final JagString aClass100_974 = JagString.parse("<br>(X");

	@OriginalMember(owner = "client!si", name = "a", descriptor = "(IZ)V")
	public static void method3935(@OriginalArg(0) int arg0) {
		FogManager.method3060(FogManager.anInt3923, ((float) arg0 * 0.1F + 0.7F) * 1.1523438F, 0.69921875F, 0.69921875F);
		FogManager.method3063(-50.0F, -60.0F, -50.0F);
		FogManager.method3062(FogManager.anInt3922, 0);
		FogManager.method3058();
	}

}
