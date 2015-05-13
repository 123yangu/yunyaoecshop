package com.yunyao.shop.common.config;

import com.jfinal.config.Constants;
import com.jfinal.config.Handlers;
import com.jfinal.config.Interceptors;
import com.jfinal.config.JFinalConfig;
import com.jfinal.config.Plugins;
import com.jfinal.config.Routes;
import com.jfinal.core.JFinal;
import com.jfinal.plugin.activerecord.ActiveRecordPlugin;
import com.jfinal.plugin.c3p0.C3p0Plugin;
import com.jfinal.render.ViewType;
import com.yunyao.shop.controller.LoginController;
import com.yunyao.shop.model.User;

public class CommonConfig extends JFinalConfig {

	@Override
	public void configConstant(Constants me) {
		loadPropertyFile("a_little_config.txt");
		me.setDevMode(getPropertyToBoolean("devMode", false));
		me.setViewType(ViewType.JSP);

	}
	
	
	@Override
	public void configRoute(Routes me) {
		me.add("/", LoginController.class);

	}

	@Override
	public void configPlugin(Plugins me) {
		
		C3p0Plugin c3p0Plugin = new C3p0Plugin(getProperty("jdbcUrl"), getProperty("user"), getProperty("password").trim());
		me.add(c3p0Plugin);

		
		ActiveRecordPlugin arp = new ActiveRecordPlugin(c3p0Plugin);
		me.add(arp);
		arp.addMapping("user", User.class);	

	}

	@Override
	public void configInterceptor(Interceptors me) {
		
	}

	@Override
	public void configHandler(Handlers me) {
		
	}
	
	public static void main(String[] args) {
		JFinal.start("WebRoot", 80, "/", 5);
	}

}
