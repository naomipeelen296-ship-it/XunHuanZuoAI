mkdir ~/.ssh
$input = @"




"@
$input | ssh-keygen -t rsa -b 4096 -C "naomipeelen296@gmail.com" -f ~/.ssh/id_rsa