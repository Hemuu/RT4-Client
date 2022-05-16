package rt4;

import org.openrs2.deob.annotation.OriginalArg;
import org.openrs2.deob.annotation.OriginalMember;
import org.openrs2.deob.annotation.Pc;

import java.io.IOException;
import java.net.Socket;

public class LoginManager {
    @OriginalMember(owner = "client!bg", name = "g", descriptor = "Lclient!i;")
    public static final Packet buffer = new Packet(5000);
    @OriginalMember(owner = "client!nd", name = "r", descriptor = "Lclient!na;")
    public static final JagString ZAP = JagString.parse("zap");
    @OriginalMember(owner = "client!ef", name = "h", descriptor = "Lclient!na;")
    public static final JagString UNZAP = JagString.parse("unzap");
    @OriginalMember(owner = "client!rl", name = "X", descriptor = "I")
    public static int anInt4937 = 0;
    @OriginalMember(owner = "client!sd", name = "X", descriptor = "Z")
    public static boolean aBoolean247 = false;
    @OriginalMember(owner = "client!sk", name = "ib", descriptor = "I")
    public static int hopTime = 0;
    @OriginalMember(owner = "client!vk", name = "b", descriptor = "I")
    public static int reply = -2;
    @OriginalMember(owner = "client!hd", name = "a", descriptor = "I")
    public static int loops = 0;
    @OriginalMember(owner = "client!ol", name = "V", descriptor = "I")
    public static int step = 0;
    @OriginalMember(owner = "client!qi", name = "A", descriptor = "I")
    public static int disallowResult = -1;
    @OriginalMember(owner = "client!wh", name = "s", descriptor = "I")
    public static int errors = 0;
    @OriginalMember(owner = "client!bj", name = "Y", descriptor = "I")
    public static int anInt673 = 0;
    @OriginalMember(owner = "client!pl", name = "i", descriptor = "I")
    public static int anInt4587 = 0;
    @OriginalMember(owner = "client!pi", name = "P", descriptor = "J")
    public static long serverKey = 0L;
    @OriginalMember(owner = "client!af", name = "c", descriptor = "I")
    public static int anInt39 = -1;
    @OriginalMember(owner = "client!pg", name = "S", descriptor = "I")
    public static int staffModLevel = 0;
    @OriginalMember(owner = "client!ud", name = "O", descriptor = "I")
	public static int blackmarks = 0;
    @OriginalMember(owner = "client!jk", name = "G", descriptor = "Z")
    public static boolean playerUnderage = false;
    @OriginalMember(owner = "client!ql", name = "c", descriptor = "Z")
    public static boolean parentalChatConsent = false;
    @OriginalMember(owner = "client!qg", name = "W", descriptor = "Z")
    public static boolean playerMember = false;
    @OriginalMember(owner = "client!aa", name = "l", descriptor = "Z")
    public static boolean mapMembers = false;
    @OriginalMember(owner = "client!gm", name = "bb", descriptor = "Z")
    public static boolean mapQuickChat = false;
    @OriginalMember(owner = "client!c", name = "hb", descriptor = "Z")
    public static boolean parentalAdvertConsent = false;

    @OriginalMember(owner = "client!dm", name = "d", descriptor = "(I)V")
    public static void clear() {
        aBoolean247 = false;
        hopTime = 0;
        reply = -3;
        loops = 0;
        step = 1;
        errors = 0;
        disallowResult = -1;
    }

    @OriginalMember(owner = "client!ch", name = "b", descriptor = "(B)V")
    public static void loopAuto() {
        if (anInt4937 == 0) {
            return;
        }
        try {
            if (++anInt673 > 1500) {
                if (Protocol.socket != null) {
                    Protocol.socket.close();
                    Protocol.socket = null;
                }
                if (anInt4587 >= 1) {
                    reply = -5;
                    anInt4937 = 0;
                    return;
                }
                anInt673 = 0;
                anInt4587++;
                anInt4937 = 1;
                if (client.worldListPort == client.worldListDefaultPort) {
                    client.worldListPort = client.worldListAlternatePort;
                } else {
                    client.worldListPort = client.worldListDefaultPort;
                }
            }
            if (anInt4937 == 1) {
                Protocol.socketRequest = GameShell.signLink.openSocket(client.worldListHostname, client.worldListPort);
                anInt4937 = 2;
            }
            @Pc(126) int local126;
            if (anInt4937 == 2) {
                if (Protocol.socketRequest.status == 2) {
                    throw new IOException();
                }
                if (Protocol.socketRequest.status != 1) {
                    return;
                }
                Protocol.socket = new BufferedSocket((Socket) Protocol.socketRequest.result, GameShell.signLink);
                Protocol.socketRequest = null;
                Protocol.socket.write(Protocol.outboundBuffer.data, Protocol.outboundBuffer.offset);
                if (client.musicChannel != null) {
                    client.musicChannel.method3571();
                }
                if (client.soundChannel != null) {
                    client.soundChannel.method3571();
                }
                local126 = Protocol.socket.read();
                if (client.musicChannel != null) {
                    client.musicChannel.method3571();
                }
                if (client.soundChannel != null) {
                    client.soundChannel.method3571();
                }
                if (local126 != 101) {
                    reply = local126;
                    anInt4937 = 0;
                    Protocol.socket.close();
                    Protocol.socket = null;
                    return;
                }
                anInt4937 = 3;
            }
            if (anInt4937 == 3) {
                if (Protocol.socket.available() < 2) {
                    return;
                }
                local126 = Protocol.socket.read() << 8 | Protocol.socket.read();
                Static176.hopWorld(local126);
                if (Player.worldId == -1) {
                    anInt4937 = 0;
                    reply = 6;
                    Protocol.socket.close();
                    Protocol.socket = null;
                    return;
                }
                anInt4937 = 0;
                Protocol.socket.close();
                Protocol.socket = null;
                clear();
            }
        } catch (@Pc(210) IOException local210) {
            if (Protocol.socket != null) {
                Protocol.socket.close();
                Protocol.socket = null;
            }
            if (anInt4587 < 1) {
                if (client.worldListPort == client.worldListDefaultPort) {
                    client.worldListPort = client.worldListAlternatePort;
                } else {
                    client.worldListPort = client.worldListDefaultPort;
                }
                anInt4937 = 1;
                anInt673 = 0;
                anInt4587++;
            } else {
                reply = -4;
                anInt4937 = 0;
            }
        }
    }

    @OriginalMember(owner = "client!ri", name = "a", descriptor = "(B)V")
    public static void loop() {
        if (step == 0 || step == 5) {
            return;
        }
        try {
            if (++loops > 2000) {
                if (Protocol.socket != null) {
                    Protocol.socket.close();
                    Protocol.socket = null;
                }
                if (errors >= 1) {
                    reply = -5;
                    step = 0;
                    return;
                }
                loops = 0;
                if (client.port == client.defaultPort) {
                    client.port = client.alternatePort;
                } else {
                    client.port = client.defaultPort;
                }
                step = 1;
                errors++;
            }
            if (step == 1) {
                Protocol.socketRequest = GameShell.signLink.openSocket(client.hostname, client.port);
                step = 2;
            }
            if (step == 2) {
                if (Protocol.socketRequest.status == 2) {
                    throw new IOException();
                }
                if (Protocol.socketRequest.status != 1) {
                    return;
                }
                Protocol.socket = new BufferedSocket((Socket) Protocol.socketRequest.result, GameShell.signLink);
                Protocol.socketRequest = null;
                @Pc(106) long local106 = Player.name37 = Player.usernameInput.encode37();
                Protocol.outboundBuffer.offset = 0;
                Protocol.outboundBuffer.p1(14);
                @Pc(120) int local120 = (int) (local106 >> 16 & 0x1FL);
                Protocol.outboundBuffer.p1(local120);
                Protocol.socket.write(Protocol.outboundBuffer.data, 2);
                if (client.musicChannel != null) {
                    client.musicChannel.method3571();
                }
                if (client.soundChannel != null) {
                    client.soundChannel.method3571();
                }
                @Pc(150) int local150 = Protocol.socket.read();
                if (client.musicChannel != null) {
                    client.musicChannel.method3571();
                }
                if (client.soundChannel != null) {
                    client.soundChannel.method3571();
                }
                if (local150 != 0) {
                    reply = local150;
                    step = 0;
                    Protocol.socket.close();
                    Protocol.socket = null;
                    return;
                }
                step = 3;
            }
            if (step == 3) {
                if (Protocol.socket.available() < 8) {
                    return;
                }
                Protocol.socket.read(0, 8, Protocol.inboundBuffer.data);
                Protocol.inboundBuffer.offset = 0;
                serverKey = Protocol.inboundBuffer.g8();
                @Pc(210) int[] key = new int[4];
                Protocol.outboundBuffer.offset = 0;
                key[2] = (int) (serverKey >> 32);
                key[3] = (int) serverKey;
                key[1] = (int) (Math.random() * 9.9999999E7D);
                key[0] = (int) (Math.random() * 9.9999999E7D);
                Protocol.outboundBuffer.p1(10);
                Protocol.outboundBuffer.p4(key[0]);
                Protocol.outboundBuffer.p4(key[1]);
                Protocol.outboundBuffer.p4(key[2]);
                Protocol.outboundBuffer.p4(key[3]);
                Protocol.outboundBuffer.p8(Player.usernameInput.encode37());
                Protocol.outboundBuffer.pjstr(Player.password);
                if (GlobalConfig.LOGIN_EXTRA_INFO) {
                    Protocol.outboundBuffer.pjstr(JagString.parse(""));
                    Protocol.outboundBuffer.pjstr(JagString.parse(""));
                    Protocol.outboundBuffer.pjstr(JagString.parse(""));
                }
                Protocol.outboundBuffer.encryptRsa(GlobalConfig.RSA_EXPONENT, GlobalConfig.RSA_MODULUS);
                buffer.offset = 0;
                if (client.gameState == 40) {
                    buffer.p1(18);
                } else {
                    buffer.p1(16);
                }
                int offset = 0;
                if (GlobalConfig.LOGIN_FAKE_IDX28) {
                    // pretend that we're loading the archive so we don't throw the packet size off
                    offset = 4;
                }
                buffer.p2(Protocol.outboundBuffer.offset + Buffer.getStringLength(client.settings) + (159 + offset));
                buffer.p4(530);
                buffer.p1(anInt39);
                buffer.p1(client.advertSuppressed ? 1 : 0);
                buffer.p1(1);
                buffer.p1(DisplayMode.getWindowMode());
                buffer.p2(GameShell.canvasWidth);
                buffer.p2(GameShell.canvasHeight);
                buffer.p1(Preferences.antiAliasingMode);
                client.writeUid(buffer);
                buffer.pjstr(client.settings);
                buffer.p4(client.affiliate);
                buffer.p4(Preferences.toInt());
                Preferences.sentToServer = true;
                buffer.p2(Protocol.verifyId);
                buffer.p4(client.js5Archive0.getChecksum());
                buffer.p4(client.js5Archive1.getChecksum());
                buffer.p4(client.js5Archive2.getChecksum());
                buffer.p4(client.js5Archive3.getChecksum());
                buffer.p4(client.js5Archive4.getChecksum());
                buffer.p4(client.js5Archive5.getChecksum());
                buffer.p4(client.js5Archive6.getChecksum());
                buffer.p4(client.js5Archive7.getChecksum());
                buffer.p4(client.js5Archive8.getChecksum());
                buffer.p4(client.js5Archive9.getChecksum());
                buffer.p4(client.js5Archive10.getChecksum());
                buffer.p4(client.js5Archive11.getChecksum());
                buffer.p4(client.js5Archive12.getChecksum());
                buffer.p4(client.js5Archive13.getChecksum());
                buffer.p4(client.js5Archive14.getChecksum());
                buffer.p4(client.js5Archive15.getChecksum());
                buffer.p4(client.js5Archive16.getChecksum());
                buffer.p4(client.js5Archive17.getChecksum());
                buffer.p4(client.js5Archive18.getChecksum());
                buffer.p4(client.js5Archive19.getChecksum());
                buffer.p4(client.js5Archive20.getChecksum());
                buffer.p4(client.js5Archive21.getChecksum());
                buffer.p4(client.js5Archive22.getChecksum());
                buffer.p4(client.js5Archive23.getChecksum());
                buffer.p4(client.js5Archive24.getChecksum());
                buffer.p4(client.js5Archive25.getChecksum());
                buffer.p4(client.js5Archive26.getChecksum());
                buffer.p4(client.js5Archive27.getChecksum());
                if (GlobalConfig.LOGIN_FAKE_IDX28) {
                    buffer.p4(0);
                }
                buffer.pBytes(Protocol.outboundBuffer.data, Protocol.outboundBuffer.offset);
                Protocol.socket.write(buffer.data, buffer.offset);
                Protocol.outboundBuffer.setKey(key);
                for (@Pc(583) int local583 = 0; local583 < 4; local583++) {
                    key[local583] += 50;
                }
                Protocol.inboundBuffer.setKey(key);
                step = 4;
            }
            if (step == 4) {
                if (Protocol.socket.available() < 1) {
                    return;
                }
                @Pc(623) int local623 = Protocol.socket.read();
                if (local623 == 21) {
                    step = 7;
                } else if (local623 == 29) {
                    step = 10;
                } else if (local623 == 1) {
                    step = 5;
                    reply = local623;
                    return;
                } else if (local623 == 2) {
                    step = 8;
                } else if (local623 == 15) {
                    step = 0;
                    reply = local623;
                    return;
                } else if (local623 == 23 && errors < 1) {
                    step = 1;
                    errors++;
                    loops = 0;
                    Protocol.socket.close();
                    Protocol.socket = null;
                    return;
                } else {
                    reply = local623;
                    step = 0;
                    Protocol.socket.close();
                    Protocol.socket = null;
                    return;
                }
            }
            if (step == 6) {
                Protocol.outboundBuffer.offset = 0;
                Protocol.outboundBuffer.p1isaac(17);
                Protocol.socket.write(Protocol.outboundBuffer.data, Protocol.outboundBuffer.offset);
                step = 4;
                return;
            }
            if (step == 7) {
                if (Protocol.socket.available() >= 1) {
                    hopTime = (Protocol.socket.read() + 3) * 60;
                    step = 0;
                    reply = 21;
                    Protocol.socket.close();
                    Protocol.socket = null;
                    return;
                }
                return;
            }
            if (step == 10) {
                if (Protocol.socket.available() >= 1) {
                    disallowResult = Protocol.socket.read();
                    step = 0;
                    reply = 29;
                    Protocol.socket.close();
                    Protocol.socket = null;
                    return;
                }
                return;
            }
            if (step == 8) {
                if (Protocol.socket.available() < 14) {
                    return;
                }
                Protocol.socket.read(0, 14, Protocol.inboundBuffer.data);
                Protocol.inboundBuffer.offset = 0;
                staffModLevel = Protocol.inboundBuffer.g1();
                blackmarks = Protocol.inboundBuffer.g1();
                playerUnderage = Protocol.inboundBuffer.g1() == 1;
                parentalChatConsent = Protocol.inboundBuffer.g1() == 1;
                parentalAdvertConsent = Protocol.inboundBuffer.g1() == 1;
                mapQuickChat = Protocol.inboundBuffer.g1() == 1;
                MouseRecorder.enabled = Protocol.inboundBuffer.g1() == 1;
                PlayerList.selfId = Protocol.inboundBuffer.g2();
                playerMember = Protocol.inboundBuffer.g1() == 1;
                mapMembers = Protocol.inboundBuffer.g1() == 1;
                LocTypeList.setAllowMembers(mapMembers);
                ObjTypeList.setAllowMembers(mapMembers);
                if (!client.advertSuppressed) {
                    if (playerUnderage && !parentalAdvertConsent || playerMember) {
                        try {
                            ZAP.browserControlCall(GameShell.signLink.applet);
                        } catch (@Pc(910) Throwable local910) {
                        }
                    } else {
                        try {
                            UNZAP.browserControlCall(GameShell.signLink.applet);
                        } catch (@Pc(920) Throwable local920) {
                        }
                    }
                }
                Protocol.opcode = Protocol.inboundBuffer.g1isaac();
                Protocol.length = Protocol.inboundBuffer.g2();
                step = 9;
            }
            if (step == 9) {
                if (Protocol.socket.available() < Protocol.length) {
                    return;
                }
                Protocol.inboundBuffer.offset = 0;
                Protocol.socket.read(0, Protocol.length, Protocol.inboundBuffer.data);
                reply = 2;
                step = 0;
                client.method4221();
                Static80.centralZoneX = -1;
                Protocol.readRebuildPacket(false);
                Protocol.opcode = -1;
                return;
            }
        } catch (@Pc(977) IOException ex) {
            if (Protocol.socket != null) {
                Protocol.socket.close();
                Protocol.socket = null;
            }
            if (errors >= 1) {
                step = 0;
                reply = -4;
            } else {
                step = 1;
                loops = 0;
                errors++;
                if (client.defaultPort == client.port) {
                    client.port = client.alternatePort;
                } else {
                    client.port = client.defaultPort;
                }
            }
        }
    }

    @OriginalMember(owner = "client!p", name = "a", descriptor = "(I)V")
    public static void method3395() {
        if (step == 5) {
            step = 6;
        }
    }

    @OriginalMember(owner = "client!se", name = "a", descriptor = "(Lclient!na;Lclient!na;IB)V")
    public static void method3896(@OriginalArg(0) JagString arg0, @OriginalArg(1) JagString arg1, @OriginalArg(2) int arg2) {
        Player.password = arg1;
        anInt39 = arg2;
        Player.usernameInput = arg0;
        if (Player.usernameInput.strEquals(JagString.EMPTY) || Player.password.strEquals(JagString.EMPTY)) {
            reply = 3;
        } else if (Player.worldId == -1) {
            anInt673 = 0;
            anInt4587 = 0;
            reply = -3;
            anInt4937 = 1;
            @Pc(43) Buffer local43 = new Buffer(128);
            local43.p1(10);
            local43.p2((int) (Math.random() * 99999.0D));
            local43.p2(530);
            local43.p8(Player.usernameInput.encode37());
            local43.p4((int) (Math.random() * 9.9999999E7D));
            local43.pjstr(Player.password);
            local43.p4((int) (Math.random() * 9.9999999E7D));
            local43.encryptRsa(GlobalConfig.RSA_EXPONENT, GlobalConfig.RSA_MODULUS);
            Protocol.outboundBuffer.offset = 0;
            Protocol.outboundBuffer.p1(210);
            Protocol.outboundBuffer.p1(local43.offset);
            Protocol.outboundBuffer.pBytes(local43.data, local43.offset);
        } else {
            clear();
        }
    }

    @OriginalMember(owner = "client!ca", name = "h", descriptor = "(I)V")
	public static void setupLoadingScreenRegion() {
		@Pc(10) int local10 = (Camera.renderX >> 10) + (Camera.originX >> 3);
		@Pc(23) int local23 = (Camera.renderZ >> 10) + (Camera.originZ >> 3);
		Static156.locationMapFilesBuffer = new byte[18][];
		Static35.underWaterLocationsMapFileIds = new int[18];
		Static191.npcSpawnsFilesBuffer = new byte[18][];
		Static36.mapFileIds = new int[18];
		Static72.regionsXteaKeys = new int[18][4];
		Static186.underWaterMapFilesBuffer = new byte[18][];
		Static238.regionBitPacked = new int[18];
		Static273.mapFilesBuffer = new byte[18][];
		Static175.npcSpawnsFileIds = new int[18];
		Static99.underWaterMapFileIds = new int[18];
		Static172.locationsMapFileIds = new int[18];
		Static19.underWaterLocationsMapFilesBuffer = new byte[18][];
		@Pc(74) int local74 = 0;
		@Pc(80) int local80;
		for (local80 = (local10 - 6) / 8; local80 <= (local10 + 6) / 8; local80++) {
			for (@Pc(97) int local97 = (local23 - 6) / 8; local97 <= (local23 + 6) / 8; local97++) {
				@Pc(115) int local115 = (local80 << 8) + local97;
				Static238.regionBitPacked[local74] = local115;
				Static36.mapFileIds[local74] = client.js5Archive5.getGroupId(JagString.concatenate(new JagString[] { Static103.aClass100_558, JagString.parseInt(local80), Static86.UNDERSCORE, JagString.parseInt(local97) }));
				Static172.locationsMapFileIds[local74] = client.js5Archive5.getGroupId(JagString.concatenate(new JagString[] { Static270.aClass100_1090, JagString.parseInt(local80), Static86.UNDERSCORE, JagString.parseInt(local97) }));
				Static175.npcSpawnsFileIds[local74] = client.js5Archive5.getGroupId(JagString.concatenate(new JagString[] { Static179.aClass100_807, JagString.parseInt(local80), Static86.UNDERSCORE, JagString.parseInt(local97) }));
				Static99.underWaterMapFileIds[local74] = client.js5Archive5.getGroupId(JagString.concatenate(new JagString[] { Static165.aClass100_772, JagString.parseInt(local80), Static86.UNDERSCORE, JagString.parseInt(local97) }));
				Static35.underWaterLocationsMapFileIds[local74] = client.js5Archive5.getGroupId(JagString.concatenate(new JagString[] { Static278.aClass100_1103, JagString.parseInt(local80), Static86.UNDERSCORE, JagString.parseInt(local97) }));
				if (Static175.npcSpawnsFileIds[local74] == -1) {
					Static36.mapFileIds[local74] = -1;
					Static172.locationsMapFileIds[local74] = -1;
					Static99.underWaterMapFileIds[local74] = -1;
					Static35.underWaterLocationsMapFileIds[local74] = -1;
				}
				local74++;
			}
		}
		for (local80 = local74; local80 < Static175.npcSpawnsFileIds.length; local80++) {
			Static175.npcSpawnsFileIds[local80] = -1;
			Static36.mapFileIds[local80] = -1;
			Static172.locationsMapFileIds[local80] = -1;
			Static99.underWaterMapFileIds[local80] = -1;
			Static35.underWaterLocationsMapFileIds[local80] = -1;
		}
		Static127.method2463(0, local23, local10, 8, true, 8);
	}
}
