YAIS
=============
Server for YAIS-Client to keep all the data in one place and have a point to collect them.  
The server is managing archiv terms and looks for better pattern to controll the archive.


Doc
=============
Wekan-Board:
    https://lb.vps.b4sh.de/b/MZECZRD7Xf5tQGLek/yais-server
    
    
Database-Structur Planning
=============
Collection planning
- 1x collection for all archive entries  
    id, shelf-row-id, shelf-id, room-id, name, createdDate, deadline(boolean), deadlineDate(nullable)
- 1x shelf-row  
    id, shelf-id, name
- 1x collection for all shelfs in the archive  
    id, room id
- 1x rooms that holds the boards  
    id, name
