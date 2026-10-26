# Shadow Hall — Kotlin Android Prototype

A starter project for KlencodIDE / Android Studio using Kotlin + OpenGL ES 2.0. The current prototype draws a simple low-poly 3D room, third-person player placeholder, chasing monster, and collectible keys.

## Import
1. In KlencodIDE, choose **Import project from ZIP** and select this ZIP.
2. If the IDE asks for Android SDK / Gradle sync, use compile SDK 35 and Android Gradle Plugin 8.6.1 with Kotlin 2.0.20, or adapt plugin versions to the versions supported by your IDE.
3. Run on an Android device in landscape mode.

## Current prototype controls
- Drag on the left half of the screen to move.
- Drag on the right half to rotate the camera.
- Tap **اختباء** to toggle hiding. This is a placeholder mechanic and is not yet restricted to nearby hiding places.
- Walk near the gold cubes to collect keys.

## Important status
- This is source code for an early playable rendering prototype, not a finished game.
- Local multiplayer over hotspot is **not implemented yet**. It needs a host/client protocol (for example, Android UDP/TCP sockets), player-state synchronization, and testing on two phones.
- Monster AI, key inventory UI, proper hiding zones, collision/navigation, audio, animations, menus, save state, and endings are next milestones.
- No APK is included because this environment does not have a configured Android SDK/Gradle build toolchain, and this project has not been built or tested on a device here.

## Design roadmap
1. **Milestone A — playable base:** third-person camera, movement, collision, key collection, chase/reset loop.
2. **Milestone B — stealth:** noise meter, monster patrol/investigate/search/chase states, actual hiding spots, stamina and flashlight battery.
3. **Milestone C — objective loop:** three-key inventory, locked basement, symbol puzzle, escape gate and two endings.
4. **Milestone D — local co-op:** one phone hosts, second phone joins over the hotspot LAN; synchronize player position, keys, monster state, revive state and game phase. Validate firewall/permissions and both phones on the same local network.
5. **Milestone E — polish:** low-poly assets, animation, sound cues, pause/settings, performance checks on Galaxy A23.
