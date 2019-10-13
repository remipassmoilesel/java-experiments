package jgroups.k8s;

import jgroups.k8s.cluster.ClusterStateListener;
import jgroups.k8s.cluster.JGroupsCoordinator;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

@Slf4j
@Component
public class LeaderElection implements ClusterStateListener {

    private final JGroupsCoordinator coordinator;

    @Autowired
    public LeaderElection(JGroupsCoordinator coordinator) {
        this.coordinator = coordinator;
    }

    @PostConstruct
    public void setup() {
        this.coordinator.addClusterStateListener(this);
    }

    @Override
    public void onBecameMaster() {
        log.warn("I am master !");
    }

    @Override
    public void onBecameWorker() {
        log.warn("I am worker !");
    }
}
