package rt4;

import org.openrs2.deob.annotation.OriginalArg;
import org.openrs2.deob.annotation.OriginalMember;
import org.openrs2.deob.annotation.Pc;

import java.awt.*;

public class Fonts {
	@OriginalMember(owner = "client!j", name = "x", descriptor = "Lclient!rk;")
	public static Font p11Full;
	@OriginalMember(owner = "client!rh", name = "h", descriptor = "Lclient!rk;")
	public static Font p12Full;
	@OriginalMember(owner = "client!wl", name = "q", descriptor = "Lclient!rk;")
	public static Font b12Full;
	@OriginalMember(owner = "client!vj", name = "j", descriptor = "Lclient!dd;")
	public static SoftwareFont p11FullSoftware;

	@OriginalMember(owner = "client!fn", name = "a", descriptor = "(Lclient!ve;Lclient!ve;Z)I")
	public static int getReady(@OriginalArg(0) Js5 js5Archive8, @OriginalArg(1) Js5 js5Archive13) {
		@Pc(5) int ready = 0;
		if (js5Archive8.isFileReady(Sprites.p11FullId)) {
			ready++;
		}
		if (js5Archive8.isFileReady(Sprites.p12FullId)) {
			ready++;
		}
		if (js5Archive8.isFileReady(Sprites.b12FullId)) {
			ready++;
		}
		if (js5Archive13.isFileReady(Sprites.p11FullId)) {
			ready++;
		}
		if (js5Archive13.isFileReady(Sprites.p12FullId)) {
			ready++;
		}
		if (js5Archive13.isFileReady(Sprites.b12FullId)) {
			ready++;
		}
		return ready;
	}

	@OriginalMember(owner = "client!ld", name = "a", descriptor = "(B)I")
	public static int getTotal() {
		return 6;
	}

	@OriginalMember(owner = "client!hn", name = "a", descriptor = "(Lclient!ve;ILclient!ve;)V")
	public static void load(@OriginalArg(0) Js5 archive13, @OriginalArg(2) Js5 archive8) {
		p11Full = Font.loadFont(Sprites.p11FullId, archive8, archive13);
		if (GlRenderer.enabled) {
			p11FullSoftware = SoftwareFont.method2412(Sprites.p11FullId, archive13, archive8);
		} else {
			p11FullSoftware = (SoftwareFont) p11Full;
		}
		p12Full = Font.loadFont(Sprites.p12FullId, archive8, archive13);
		b12Full = Font.loadFont(Sprites.b12FullId, archive8, archive13);
	}

	@OriginalMember(owner = "client!j", name = "a", descriptor = "(BZLclient!na;)V")
	public static void drawTextOnScreen(@OriginalArg(1) boolean arg0, @OriginalArg(2) JagString text) {
		@Pc(24) int lineWidth = p12Full.getMaxLineWidth(text, 250);
		@Pc(31) int lineCount = p12Full.getParagraphLineCount(text, 250) * 13;
		if (GlRenderer.enabled) {
			GlRaster.fillRect(6, 6, lineWidth + 4 + 4, lineCount + 8, 0);
			GlRaster.drawRect(6, 6, lineWidth + 4 + 4, lineCount + 4 + 4, 16777215);
		} else {
			SoftwareRaster.fillRect(6, 6, lineWidth + 4 + 4, lineCount + 8, 0);
			SoftwareRaster.drawRect(6, 6, lineWidth + 8, 4 + 4 + lineCount, 16777215);
		}
		p12Full.drawInterfaceText(text, 10, 10, lineWidth, lineCount, 16777215, -1, 1, 1, 0);
		InterfaceList.redrawScreen(6, lineWidth + 8, 6, lineCount + 4 + 4);
		if (!arg0) {
			InterfaceList.forceRedrawScreen(10, 10, lineCount, lineWidth);
		} else if (GlRenderer.enabled) {
			GlRenderer.swapBuffers();
		} else {
			try {
				@Pc(159) Graphics local159 = GameShell.canvas.getGraphics();
				SoftwareRaster.frameBuffer.draw(local159);
			} catch (@Pc(167) Exception local167) {
				GameShell.canvas.repaint();
			}
		}
	}
}
