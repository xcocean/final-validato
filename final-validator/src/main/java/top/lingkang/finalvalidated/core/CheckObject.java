package top.lingkang.finalvalidated.core;

import top.lingkang.finalvalidated.handle.ValidHandle;

import java.util.List;

/**
 * @author lingkang
 * Created by 2024/1/26
 */
class CheckObject {
    private boolean need;
    private List<ValidHandle> handles;

    public List<ValidHandle> getHandles() {
        return handles;
    }

    public void setHandles(List<ValidHandle> handles) {
        this.handles = handles;
    }

    public boolean isNeed() {
        return need;
    }

    public void setNeed(boolean need) {
        this.need = need;
    }
}
