// logic/StateMapper.java
public class StateMapper {

    // Convertit java.awt.Taskbar.State → logic.State
    public static State toLogicState(java.awt.Taskbar.State guiState) {
        if (guiState == java.awt.Taskbar.State.NORMAL) return State.True;
        if (guiState == java.awt.Taskbar.State.OFF) return State.False;
        if (guiState == java.awt.Taskbar.State.ERROR) return State.ERROR;
        return State.UNKNOWN; // Pour INDETERMINATE ou autre
    }

    // Convertit logic.State → java.awt.Taskbar.State
    public static java.awt.Taskbar.State toGuiState(State logicState) {
        if (logicState == State.True) return java.awt.Taskbar.State.NORMAL;
        if (logicState == State.False) return java.awt.Taskbar.State.OFF;
        if (logicState == State.ERROR) return java.awt.Taskbar.State.ERROR;
        return java.awt.Taskbar.State.INDETERMINATE;
    }
}

