



# test curl
while true;do  curl userlist.ipc.kt.com/users/1 ;  sleep 0.5; echo; done

# sticky sesstion set
vi DestinationRule.yaml
...
  trafficPolicy:
    loadBalancer:
      consistentHash:
        useSourceIp: true
        #httpCookie:
        #  name: JSESSIONID
        #  ttl: 10800s





