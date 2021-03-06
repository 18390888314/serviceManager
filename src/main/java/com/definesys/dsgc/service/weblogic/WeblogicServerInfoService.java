package com.definesys.dsgc.service.weblogic;

import com.definesys.dsgc.bean.WeblogicServesInfo;
import org.springframework.stereotype.Component;

import javax.management.MBeanServerConnection;
import javax.management.MalformedObjectNameException;
import javax.management.ObjectName;
import javax.management.remote.JMXConnector;
import javax.management.remote.JMXConnectorFactory;
import javax.management.remote.JMXServiceURL;
import javax.naming.Context;
import java.io.IOException;
import java.net.MalformedURLException;
import java.util.Hashtable;
@Component
public class WeblogicServerInfoService {

	private static MBeanServerConnection connection;
    public static JMXConnector connector;

    private static ObjectName runtimeService;

    private static final String RUNTIMESERVICEMBEAN =
        "com.bea:Name=DomainRuntimeService,Type=weblogic.management.mbeanservers.domainruntime.DomainRuntimeServiceMBean";
    //主机名
    private static String hostname;
    //控制台端口
    private static String portString;

    /**
     * 初始化连接
     *
     * @throws IOException
     * @throws MalformedURLException
     * @throws MalformedObjectNameException
     */
    public void initConnection(String hostname, String portString,
                                      String username,
                                      String password) throws IOException,
                                                              MalformedURLException,
                                                              MalformedObjectNameException {
        String protocol = "t3";
        WeblogicServerInfoService.hostname = hostname;
        WeblogicServerInfoService.portString = portString;
        Integer portInteger = Integer.valueOf(portString);
        int port = portInteger.intValue();
        String jndiroot = "/jndi/";
        String mserver = "weblogic.management.mbeanservers.domainruntime";
        JMXServiceURL serviceURL =
            new JMXServiceURL(protocol, hostname, port, jndiroot + mserver);

        Hashtable<String, String> h = new Hashtable<String, String>();
        h.put(Context.SECURITY_PRINCIPAL, username);
        h.put(Context.SECURITY_CREDENTIALS, password);
        h.put(JMXConnectorFactory.PROTOCOL_PROVIDER_PACKAGES,
              "weblogic.management.remote");

        connector = JMXConnectorFactory.connect(serviceURL, h);
        connection = connector.getMBeanServerConnection();

        runtimeService = new ObjectName(RUNTIMESERVICEMBEAN);
    }

    public ObjectName[] getJDBCDataSourceRuntime(ObjectName JDBCServiceRuntime ) throws Exception   {
        ObjectName[] jdbcDataSourceRTMB = (ObjectName[]) connection.getAttribute(JDBCServiceRuntime, "JDBCDataSourceRuntimeMBeans");
        return jdbcDataSourceRTMB;
    }

    public void printJdbc( ObjectName[] objectList) throws Exception {
//        ObjectName[] objectList = getJDBCDataSourceRuntime();
        if(objectList != null && objectList.length > 0){
            for(ObjectName obj : objectList){
                int connectionsTotalCount = (int) connection.getAttribute(obj, "ConnectionsTotalCount");
                int activeConnectionsCurrentCount = (int)connection.getAttribute(obj, "ActiveConnectionsCurrentCount");
                int activeConnectionsAverageCount = (int)connection.getAttribute(obj,"ActiveConnectionsAverageCount");
                int failuresToReconnectCount = (int)connection.getAttribute(obj, "FailuresToReconnectCount");
                String name = (String)connection.getAttribute(obj, "Name");
                String State = (String)connection.getAttribute(obj, "State");
                int waitingForConnectionCurrentCount = (int)connection.getAttribute(obj, "WaitingForConnectionCurrentCount");
                long waitingForConnectionFailureTotal = (long)connection.getAttribute(obj, "WaitingForConnectionFailureTotal");
                int waitSecondsHighCount = (int)connection.getAttribute(obj, "WaitSecondsHighCount");
                int connectionDelayTime = (int)connection.getAttribute(obj, "ConnectionDelayTime");
                int activeConnectionsHighCount = (int)connection.getAttribute(obj, "ActiveConnectionsHighCount");
                int currCapacity = (int)connection.getAttribute(obj, "CurrCapacity");
//                System.out.println("  ===============     ");
//                System.out.println("currCapacity==" + currCapacity); // 当前使用量
//                System.out.println("activeConnectionsHighCount==" + activeConnectionsHighCount); // 最大活动连接计数
//                System.out.println("connectionDelayTime==" + connectionDelayTime);
//                System.out.println("connectionsTotalCount==" + connectionsTotalCount); // 总链接数
//                System.out.println("activeConnectionsCurrentCount==" + activeConnectionsCurrentCount); //当前活动连接计数
//                System.out.println("activeConnectionsAverageCount==" + activeConnectionsAverageCount); //活动连接平均计数
//                System.out.println("failuresToReconnectCount==" + failuresToReconnectCount);
//                System.out.println("name==" + name); // 数据源名字
//                System.out.println("State==" + State); // 数据源状态
//                System.out.println("waitingForConnectionCurrentCount==" +waitingForConnectionCurrentCount ); // 等待链接数
//                System.out.println("waitingForConnectionFailureTotal==" + waitingForConnectionFailureTotal); //
//                System.out.println("waitSecondsHighCount==" + waitSecondsHighCount);
//                System.out.println("  ===============     ");

            }
        }
    }
    
    /**
     * 获取首页实时服务器数据
     * @param serverRuntime
     * @return
     * @throws Exception
     */
    public WeblogicServesInfo getServerRuntimeForFirstPage(ObjectName serverRuntime) throws Exception{
    	WeblogicServesInfo server = new WeblogicServesInfo();


        ObjectName JDBCServiceRuntime =
                getAttribute(serverRuntime, "JDBCServiceRuntime");
        ObjectName[] JDBCDataSourceRuntimes = getJDBCDataSourceRuntime(JDBCServiceRuntime);
        printJdbc(JDBCDataSourceRuntimes);


    	ObjectName JVMRuntime = getAttribute(serverRuntime, "JVMRuntime");
    	// 获取线程池运行时
        ObjectName threadPoolRuntime =
            getAttribute(serverRuntime, "ThreadPoolRuntime");
    	// 服务名称 Name
        String serverName = getAttribute(serverRuntime, "Name");
        // 状态 State
        String state = getAttribute(serverRuntime, "State");
        //jvm空闲百分比
        Integer heapFreePercent = getAttribute(JVMRuntime, "HeapFreePercent");       
        //线程总数
        Integer executeThreadTotalCount =
            getAttribute(threadPoolRuntime, "ExecuteThreadTotalCount");
        //备用线程数
        Integer standbyThreadCount =
            getAttribute(threadPoolRuntime, "StandbyThreadCount");
        //活动线程数
        Integer activeExeThreadCount =
            executeThreadTotalCount - standbyThreadCount;
        server.setService(serverName);
        server.setServiceStatus(state);
        server.setHeapFreePercent(String.valueOf(heapFreePercent));
        server.setExecuteThreadTotalCount(String.valueOf(executeThreadTotalCount));
        server.setActiveExeThreadCount(String.valueOf(activeExeThreadCount));
        return server;
    }
    
    /**
     * 获取所有服务器的名称
     * @param serverRuntime
     * @return
     * @throws Exception
     */
    public String getServerName(ObjectName serverRuntime) throws Exception{
    	// 服务名称 Name
        String serverName = getAttribute(serverRuntime, "Name");
        return serverName;
    }
    
    /**
     * 获取weblogic属性参数
     *
     * @param objectName
     * @param name
     * @return
     */
    @SuppressWarnings("unchecked")
    public  <T> T getAttribute(ObjectName objectName, String name) {
        Object obj = null;
        try {
            obj = connection.getAttribute(objectName, name);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return (T)obj;
    }

    /**
     *获取状态为Running的服务器
     * @return
     * @throws Exception
     */
    public ObjectName[] getServerRuntimes() throws Exception {
        return (ObjectName[])connection.getAttribute(runtimeService,
                                                     "ServerRuntimes");
    }

    /**
     * 获取所有服务器
     * @return
     * @throws Exception
     */
    public ObjectName[] getServer() throws Exception {

        ObjectName domainconfig =
            (ObjectName)connection.getAttribute(runtimeService,
                                                "DomainConfiguration");
        ObjectName[] servers =
            (ObjectName[])connection.getAttribute(domainconfig, "Servers");
        return servers;
    }
}
