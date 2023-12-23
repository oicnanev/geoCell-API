# GeoCell API
RESTfull API - Spring - Kotlin

## Oracle Cloud Ifrastucture - Server

### Creation

To account for a PostgreSQL server, nginx load balancer server and apache Tomcat server, the minimum requirements are:

- 4 vCPUs, either ```x86_64``` or ```arm64``` architecture. Also, higher clock frequency is preferred to higher core count
- 8 GB RAM
- A supported version of a common Linux distribuition (Ubuntu 22.04)
- 100 GB free space in local file system
- OpenSSH server version 7.9p1 or later
- Enabled Swap is recommended

Chosen an VM located in Madrid with Ampere arm processator with 4 cores and 24 GB memory

### Configuration
Donwload the private key upon VM creation and the change its permissions
```terminal
chmod 400 private.key
```

Then connect to the VM using SSH
```terminal
ssh -i private.key [vm-ip-address]
```

Create a project folder and update the machine
```terminal
mkdir geocell
sudo apt update && sudo apt upgrade -y && sudo apt autoremove -y
```

If the upgrade install new kernel its advisable to restart the system and then connect again by SSH
```terminal
sudo reboot
```

Packages to install:

- Java Development Kit
- Kotlin
- Gradle
- PostgreSQL
- Postgis

```terminal
sudo apt install openjdk-11-jdk kotlin libgradle-plugins-java postgresql postgresql-client postgresql-common postgresql-contrib postgis
```

## PostgreSQL configuration

## Remote IDE configuration
Since working remotely became a necessity, we will use IntelliJ IDEA, that offers the Remote Development funcionality to help with code, run, debug and deploy projects remotely.

### Connection via SSH
The connection to a remote server is done via SSH and can be started right from the welcome screen of IntelliJ IDEA.
![](img/idea-ssh1.png)

Connect to a remote server and open the remote project

1. Ensure the Remote Development Gateway plugin is enable
2. On the IntelliJ IDEA welcome screen, select **Remote Development**. Alternatively, go to **File | Remote Development**
3. Under **SSH Connection**, click **New Connection**
4. Configure the remote server connection parameters and click **CheckConnection and Continue** to make sure the connection to the remote server is successfull.
![](img/idea-ssh2.png)
5. On the next page of the wizard, in the **IDE version** field, select the source of the remote IDE that you want to use.
![](img/idea-ssh3.png)
6. Click **Download and Start IDE**
IntelliJ IDEA starts JetBrains Gateway, which downloads the IDE backend, launches, and opens JetBrains Client with your remote project.