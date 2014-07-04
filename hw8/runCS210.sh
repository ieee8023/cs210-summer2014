ncat -v -k -l 6801 -e "/usr/bin/java CatServer" 2>&1 | tee log.txt &
