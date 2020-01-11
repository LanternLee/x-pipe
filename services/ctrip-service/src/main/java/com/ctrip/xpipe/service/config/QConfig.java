package com.ctrip.xpipe.service.config;

import com.ctrip.xpipe.config.AbstractConfig;
import qunar.tc.qconfig.client.MapConfig;
import qunar.tc.qconfig.client.PropertiesChange;
import qunar.tc.qconfig.client.PropertyItem;

import java.util.Map;

/**
 * @author wenchao.meng
 *
 * Jul 21, 2016
 */
public class QConfig extends AbstractConfig implements MapConfig.PropertiesChangeListener {

	private final String XPIPE_CONSOLE_PROPERTIES = "xpipe-console.properties";
	private final MapConfig config = MapConfig.get(XPIPE_CONSOLE_PROPERTIES);
	
	public QConfig() {
		config.addPropertiesListener(this);
	}

	@Override
	public String get(String key) {
		return config.asMap().get(key);
	}


	@Override
	public String get(String key, String defaultValue) {
		return config.asMap().getOrDefault(key, defaultValue);
	}

	@Override
	public int getOrder() {
		return HIGHEST_PRECEDENCE;
	}

	@Override
	public void onChange(PropertiesChange propertiesChange) {
		for (Map.Entry<String, PropertyItem> entry : propertiesChange.getItems().entrySet()) {
			notifyConfigChange(entry.getKey(), entry.getValue().getOldValue(), entry.getValue().getNewValue());
		}
	}
}
