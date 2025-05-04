// logic/StateMapper.java
package GRAPHIQUE ;
import Logique.Principale.*;
public class StateMapper {

    // Convertit java.awt.Taskbar.State → logic.State
    public static Logique.Principale.State toLogicState(java.awt.Taskbar.State guiState) {
        if (guiState == java.awt.Taskbar.State.NORMAL) return Logique.Principale.State.TRUE;
        if (guiState == java.awt.Taskbar.State.OFF) return Logique.Principale.State.FALSE;
        if (guiState == java.awt.Taskbar.State.ERROR) return Logique.Principale.State.ERROR;
        return Logique.Principale.State.UNKNOWN; // Pour INDETERMINATE ou autre
    }

    // Convertit logic.State → java.awt.Taskbar.State
    public static java.awt.Taskbar.State toGuiState(Logique.Principale.State logicState) {
        if (logicState == Logique.Principale.State.TRUE) return java.awt.Taskbar.State.NORMAL;
        if (logicState == Logique.Principale.State.FALSE) return java.awt.Taskbar.State.OFF;
        if (logicState == Logique.Principale.State.ERROR) return java.awt.Taskbar.State.ERROR;
        return java.awt.Taskbar.State.INDETERMINATE;
    }
}

