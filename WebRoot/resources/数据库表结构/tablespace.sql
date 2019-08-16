create temporary tablespace SYNC_temp
tempfile 'D:\SoftWare\app\Administrator\oradata\orcl\SYNC_temp.dbf' 
size 50m  
autoextend on  
next 50m maxsize 20480m  
extent management local;  
 

create tablespace SYNC_data
logging  
datafile 'D:\SoftWare\app\Administrator\oradata\orcl\SYNC_data.dbf' 
size 50m  
autoextend on  
next 50m maxsize 20480m  
extent management local;  

create user SYNC identified by SYNC  
default tablespace SYNC_data  
temporary tablespace SYNC_temp;
 

grant connect,resource,dba to SYNC;
