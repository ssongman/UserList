# 1. java 설치 (패키지 업데이트 + 만든사람 표시)
FROM    java
MAINTAINER ssongmantop@gmail.com
RUN     apt-get -y update

# 2. 소스 복사
ADD ./target/UserList.jar /usr/src/app/UserList.jar

# 3. 실행 디렉토리 설정
WORKDIR /usr/src/app

# 4. App 서버 실행 (Listen 포트 정의)
EXPOSE 8181
CMD     java -jar UserList.jar
