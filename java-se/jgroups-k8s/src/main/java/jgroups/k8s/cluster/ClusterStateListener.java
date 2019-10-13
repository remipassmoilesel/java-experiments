package jgroups.k8s.cluster;

public interface ClusterStateListener {

    public void onBecameMaster();
    public void onBecameWorker();
    
}
