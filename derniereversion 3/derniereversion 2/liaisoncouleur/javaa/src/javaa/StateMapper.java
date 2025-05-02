// logic/StateMapper.java
import Principale.*;
public class StateMapper {

    // Convertit java.awt.Taskbar.State → logic.State
    public static Principale.State toLogicState(java.awt.Taskbar.State guiState) {
        if (guiState == java.awt.Taskbar.State.NORMAL) return Principale.State.TRUE;
        if (guiState == java.awt.Taskbar.State.OFF) return Principale.State.FALSE;
        if (guiState == java.awt.Taskbar.State.ERROR) return Principale.State.ERROR;
        return Principale.State.UNKNOWN; // Pour INDETERMINATE ou autre
    }

    // Convertit logic.State → java.awt.Taskbar.State
    public static java.awt.Taskbar.State toGuiState(Principale.State logicState) {
        if (logicState == Principale.State.TRUE) return java.awt.Taskbar.State.NORMAL;
        if (logicState == Principale.State.FALSE) return java.awt.Taskbar.State.OFF;
        if (logicState == Principale.State.ERROR) return java.awt.Taskbar.State.ERROR;
        return java.awt.Taskbar.State.INDETERMINATE;
    }
}

