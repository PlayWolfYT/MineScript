# Getting Started

1. To get started with this project, you first have to `git clone` this repository.

**Note:** Make sure you have Maven installed on your system for the next steps

2. Run `mvn paper-nms:init` to initialize `nms` (net.minecraft.server, used for stuff related to sending packets)


3. Make changes to the code

4. Run `mvn package` to generate a plugin jar file.

## During development

For ease of access, I have included a `launch.json` file in the `.vscode` folder. In VSCode you can press `F5` to start a debug session on Port `5005`, with which you can use Hot-Code-Replacement when testing the plugin.

To use this, make sure to start your server with the following argument:
```
java -agentlib:jdwp=transport=dt_socket,server=y,suspend=n,address=5005 -jar your_server.jar
```

Or alternatively, you can press `F1` in VSCode and type `Tasks: Run Task` and run the `Start Server` task, after creating a directory called `server` in the project root folder and adding your desired Server-Jar (must be named `server.jar`) in it.

---

# Happy Coding