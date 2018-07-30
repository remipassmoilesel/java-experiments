# Appeler une méthode avant la destruction d'un bean

Utiliser @PreDestroy:

    @PreDestroy
    public void removeSystemTray() {
        if (trayIcon != null) {
            final SystemTray tray = SystemTray.getSystemTray();
            tray.remove(trayIcon);
        }
    }
    
Si l'annotation est ajoutée à un bean de type Singleton, la méthode sera
 appelée peu avant l'arrêt d'un serveur intégré SpringBoot 