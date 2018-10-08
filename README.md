1.structure: 
    backend:Springboot+mybatis(mybatis-plus)+sqlite
    front:vue+html+css+js
    
2.deployment:
  for backend:
    package the war (you can use deploy/occupation.war directory skip this step)
        use intellj idea
        set src/main/application.properties  occupation.runtime=true
        build->build artifact->occupation.war
        
    put occupation.war in tomcat/webapps/ directory
    like webapps/occupation.war
    
    for front: https://github.com/PanJiaChen/vue-element-admin
    
  
   
    
