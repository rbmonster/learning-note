# docker 

```
docker run -d -p 80:80 docker/getting-started
```
- `-d `: run the container in detached mode (in the background)
- `-p 80:80`: map port 80 of the host to port 80 in the container
- `docker/getting-started`: the image to use

```
docker build -t getting-started .
```
`-t`: flag tags our image,Think of this simply as a human-readable name for the final image. 

## Replace the old container

- `docker ps`： 查看所有容器
- `docker stop <the-container-id>` ： 停止容器运行
- `docker rm <the-container-id>`：删除容器运行
- `docker image ls`： 查看所有镜像


`docker tag getting-started YOUR-USER-NAME/getting-started`：交换并赋予容器一个新的名字


`docker volume create todo-db` ：创建数据卷

`docker run -dp 3000:3000 -v todo-db:/etc/todos getting-started`
- `-v`：-v flag to specify a volume mount.We will use the named volume and mount it to /etc/todos, which will capture all files created at the path.

`docker volume inspect todo-db` ： 查看数据卷的信息，可以看到具体数据卷的挂在机器地址

`docker logs -f <container-id>`：查看日志

```
docker run -dp 3000:3000 \
     -w /app -v "$(pwd):/app" \
     node:12-alpine \
     sh -c "yarn install && yarn run dev"
```
效果为Node动态监控app目录下面的代码，并进行热部署
- `-w /app`: sets the “working directory” or the current directory that the command will run from
- `-v "$(pwd):/app"`:bind mount the current directory from the host in the container into the /app directory
- `node:12-alpine`: the image to use. Note that this is the base image for our app from the Dockerfile
- `sh -c "yarn install && yarn run dev"`  the command. We’re starting a shell using sh  and running yarn install to install all dependencies and then running yarn run dev.




`docker network create todo-app` ：创建容器网络