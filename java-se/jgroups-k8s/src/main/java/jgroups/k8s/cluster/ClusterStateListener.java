package jgroups.k8s.cluster;

public interface ClusterStateListener {

    public void onMasterRole();
    public void onWorkerRole();

}
