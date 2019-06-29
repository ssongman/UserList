

os create -f 11.userlist-deployment-v1.yaml
os create -f 12.userlist-deployment-v2.yaml
os create -f 14.userlist-svc.yaml
os create -f 17.userlist-vsvc-ratio.yaml
oi create -f 19.userlist-route.yaml

os delete -f 11.userlist-deployment-v1.yaml
os delete -f 12.userlist-deployment-v2.yaml
os delete -f 14.userlist-svc.yaml
os delete -f 17.userlist-vsvc-ratio.yaml
oi delete -f 19.userlist-route.yaml


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





