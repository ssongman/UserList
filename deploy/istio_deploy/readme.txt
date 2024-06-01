#### istio-system ####
alias oi='oc -nistio-system'

#### dev-song ####
alias os='oc -ndev-song'

os create -f 11.userlist-deployment.yaml
os create -f 12.userlist-svc.yaml
os create -f 13.userlist-gateway-vs.yaml
os create -f 14.userlist-route.yaml

os delete -f 11.userlist-deployment.yaml
os delete -f 12.userlist-service.yaml
os delete -f 13.userlist-gateway-vs.yaml
os delete -f 14.userlist-route.yaml

os apply -f 11.userlist-deployment.yaml
os apply -f 12.userlist-service.yaml
os apply -f 13.userlist-gateway-vs.yaml
os apply -f 14.userlist-route.yaml

# service test
curl userlist-svc.dev-song.svc.cluster.local/users/1
while true; do curl userlist-svc.dev-song.svc.cluster.local/users/1; echo ""; sleep 1; done

# routing test
curl userlist.ipc.kt.com/users/1
while true; do curl userlist.ipc.kt.com/users/1; echo ""; sleep 1; done
