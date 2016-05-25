#!/bin/bash

if [ -n "$1" ];then
   echo "\$1 is $1"
else
   echo "\$1 can't empty."
   exit 1
fi

#---------------------------
url_ftp='moz.douins.com'
name_ftp='douins'
passwd_ftp='douins'
path_remote='/agency-api'
path_local="${2}"
#---------------------------


cd ${path_local}
name_path_md5sum=`md5 -r ${1} | cut -d" " -f1`
mkdir "${name_path_md5sum}"
cp ${1} "${name_path_md5sum}"
tar czvf "${name_path_md5sum}".tar.gz "${name_path_md5sum}"

file_tar_gz="${name_path_md5sum}.tar.gz"

 
ftp -n<<!
open ${url_ftp} 
user ${name_ftp} ${passwd_ftp}
binary
cd ${path_remote}
lcd ${path_local}
prompt
put ${file_tar_gz}
close
bye
!

echo "`clear`${1} has been transfered to moz.douins.com as ${file_tar_gz}"
rm  -rf  "${name_path_md5sum}" 
rm  -rf  "${file_tar_gz}" 
