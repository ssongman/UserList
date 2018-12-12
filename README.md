"# UserList" 

## 사용법

- 이미지 빌드
```bash
docker build -f Dockerfile_userlist -t ssongman/userlist:v1 .
```



- 실행테스트
```bash
docker run -d --name userlist1 -p 8181:8181 ssongman/userlist:v1
docker run -d --name userlist2 -p 8182:8181 ssongman/userlist:v1
curl http://localhost:8181/
curl http://localhost:8182/
```


- Dockerfile_userlist

```bash
# 1. java 설치 (패키지 업데이트 + 만든사람 표시)
FROM    java
MAINTAINER ssongmantop@gmail.com
RUN     apt-get -y update

# 2. 소스 복사
ADD ./UserList.jar /usr/src/app/UserList.jar

# 3. 실행 디렉토리 설정
WORKDIR /usr/src/app

# 4. App 서버 실행 (Listen 포트 정의)
EXPOSE 8181
CMD     java -jar UserList.jar
```



- docker-compose.yml

```bash
version: '2'

# 서비스 정의
services:
   userlist1:
      image: localhost:5000/ssongman/userlist:v1
      restart: always
      ports:
        - "8181:8181"
      container_name: userlist1
   userlist2:
      image: localhost:5000/ssongman/userlist:v1
      restart: always
      ports:
        - "8182:8181"
      container_name: userlist2

networks:
    default:
        external:
            name: my_network
```





## docker-compose 명령들

- 개별실행 test

```bash
sudo docker-compose ps    userlist1
sudo docker-compose pull  userlist1
sudo docker-compose up -d userlist1
sudo docker-compose stop  userlist1
sudo docker-compose rm -f userlist1

sudo docker-compose ps    userlist2
sudo docker-compose pull  userlist2
sudo docker-compose up -d userlist2
sudo docker-compose stop  userlist2
sudo docker-compose rm -f userlist2

sudo docker-compose ps
sudo docker-compose down           # <-- stop, remove 를 한꺼번에
```


* pull and run -- 무중단배포 실행

```bash
sh ./deploy.sh
```


- deploy.sh

```bash
#!/bin/sh

EXIST_USERLIST1=$(docker-compose ps userlist1 | grep Up)

if [ -z "$EXIST_USERLIST1" ]; then
    sudo docker-compose pull  userlist1
    sudo docker-compose up -d userlist1

    sleep 15

    sudo docker-compose stop  userlist2
    sudo docker-compose rm -f userlist2
else
    sudo docker-compose pull  userlist2
    sudo docker-compose up -d userlist2

    sleep 15

    sudo docker-compose stop  userlist1
    sudo docker-compose rm -f userlist1
fi
```

## you can see the operating this

this is Blue Green Architecture.
![Blue Green Architecture](https://github.com/ssongman/UserList/blob/master/BG_using_haproxy.png?raw=true)

- HA proxy : http://35.233.240.59:8180/
- Blue : http://35.233.240.59:8181/
- Green : http://35.233.240.59:8182/

