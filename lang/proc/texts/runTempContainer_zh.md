## 临时容器

这将使用指定的映像运行一个临时容器，一旦停止，该容器将自动移除。即使容器映像中没有指定要运行的命令，容器也会继续运行。

如果您想使用某个容器映像快速设置某个环境，这将非常有用。然后，您可以像在 XPipe 中一样正常进入容器，执行您的操作，并在不再需要时停止容器。容器会自动移除。