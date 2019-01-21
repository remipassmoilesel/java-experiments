# Concepts basiques
 
## Vocabulaire Kafka

- **records**: message, enregistrement 
- **topic**: sujet, regroupement de messages
- **producers**: Produisent des records
- **consumers**: Consomme des records
- **connectors**: Integration entrée / sortie de sources de données externes


## Utilisations possibles

- Broker de messagerie
- Stockage: les topics peuvent être très gros (100aines de tb ?)
- 


## Garantie d'ordre

L'ordre est garantie sur les partitions de topic. Pour avoir un ordre global, utiliser 
une partition pour un topic, mais perte de scalabilité.


## Connecteurs

### https://github.com/jcustenborder/kafka-connect-spooldir

Connecteur CSV, peut surveiller des répertoires, produit des messages typés.