/**
 * 
 */

var ant = {
		
		 config : {
		        type : "org.nutz.ioc.impl.PropertiesProxy",
		        fields : {
		            paths : ["jdbc.properties"]
		        }
		 },

/* BoneCp*/
        dataSourceFX : {
            type : "com.jolbox.bonecp.BoneCPDataSource",
            events : {

                depose : 'close'
            },
            fields : {
            	driverClass : {java :"$config.get('ant.driver')"},
            	jdbcUrl : {java :"$config.get('ant.url')"},
                username : {java :"$config.get('ant.user')"},
                password : {java :"$config.get('ant.password')"},
                idleConnectionTestPeriodInMinutes : 240,
                idleMaxAgeInMinutes :60 ,
                maxConnectionsPerPartition : 30,
                minConnectionsPerPartition : 10,
                partitionCount:3,
                acquireIncrement :5,
                statementsCacheSize : 100
            }
        },
        
        
        daoFX : {
            type : "org.nutz.dao.impl.NutDao",
            args : [{refer:"dataSourceFX"}]
        }
};
