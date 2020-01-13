package com.ctrip.xpipe.redis.console.alert;

import static com.ctrip.xpipe.redis.console.alert.policy.receiver.EmailReceiver.EMAIL_DBA;
import static com.ctrip.xpipe.redis.console.alert.policy.receiver.EmailReceiver.EMAIL_XPIPE_ADMIN;

/**
 * @author wenchao.meng
 *         <p>
 *         Aug 16, 2017
 */
public enum ALERT_TYPE {

    CLIENT_INSTANCE_NOT_OK("client_status", EMAIL_DBA | EMAIL_XPIPE_ADMIN) {
        @Override
        public boolean urgent() {
            return false;
        }

        @Override
        public boolean reportRecovery() {
            return true;
        }

        @Override
        public DetailDesc detailDesc() {
            return new DetailDesc("CRedis instance down", "CRedis instance unreadable or unusable");
        }
    },
    QUORUM_DOWN_FAIL("quorum_fail", EMAIL_XPIPE_ADMIN) {
        @Override
        public boolean urgent() {
            return false;
        }

        @Override
        public boolean reportRecovery() {
            return false;
        }

        @Override
        public DetailDesc detailDesc() {
            return new DetailDesc("Quorum Down Fail", "Quorum Down Fail");
        }
    },
    SENTINEL_RESET("stl_rst", EMAIL_XPIPE_ADMIN) {
        @Override
        public boolean urgent() {
            return false;
        }

        @Override
        public boolean reportRecovery() {
            return false;
        }

        @Override
        public DetailDesc detailDesc() {
            return new DetailDesc("", "");
        }
    },
    REDIS_CONF_REWRITE_FAILURE("redis_conf_rewrite_failure", EMAIL_DBA|EMAIL_XPIPE_ADMIN) {
        @Override
        public boolean urgent() {
            return false;
        }

        @Override
        public boolean reportRecovery() {
            return false;
        }

        @Override
        public DetailDesc detailDesc() {
            return new DetailDesc("Redis CONF REWRITE", "Redis CONF REWRITE unavailable will lead to redis master DR switch fail");
        }
    },
    CLIENT_INCONSIS("client_inconsis", EMAIL_DBA | EMAIL_XPIPE_ADMIN) {
        @Override
        public boolean urgent() {
            return false;
        }

        @Override
        public boolean reportRecovery() {
            return true;
        }

        @Override
        public DetailDesc detailDesc() {
            return new DetailDesc("CRedis | XPipe Inconsistent", "Inconsistent between CRedis and XPipe");
        }
    },
    MIGRATION_MANY_UNFINISHED("migra_unfinish", EMAIL_XPIPE_ADMIN) {
        @Override
        public boolean urgent() {
            return false;
        }

        @Override
        public boolean reportRecovery() {
            return false;
        }

        @Override
        public DetailDesc detailDesc() {
            return new DetailDesc("Found migration fail", "please check migration history");
        }
    },
    XREDIS_VERSION_NOT_VALID("xredis_version_not_valid", EMAIL_DBA) {
        @Override
        public boolean urgent() {
            return false;
        }

        @Override
        public boolean reportRecovery() {
            return true;
        }

        @Override
        public DetailDesc detailDesc() {
            return new DetailDesc("redis version not valid in sub dc", "redis in sub dc should be XRedis and version >= 0.0.3");
        }
    },
    REDIS_REPL_DISKLESS_SYNC_ERROR("redis_repl_diskless_sync_error", EMAIL_DBA) {
        @Override
        public boolean urgent() {
            return false;
        }

        @Override
        public boolean reportRecovery() {
            return true;
        }

        @Override
        public DetailDesc detailDesc() {
            return new DetailDesc("Redis config error", "repl-diskless-sync should be 'NO' for redis whose version under 2.8.22");
        }
    },
    MARK_INSTANCE_UP("mark instance up", EMAIL_DBA) {
        @Override
        public boolean urgent() {
            return false;
        }

        @Override
        public boolean reportRecovery() {
            return false;
        }

        @Override
        public DetailDesc detailDesc() {
            return new DetailDesc("Instance Mark UP", "redis instance will be pull into cluster after recovery");
        }
    },
    MARK_INSTANCE_DOWN("mark instance down", EMAIL_DBA) {
        @Override
        public boolean urgent() {
            return false;
        }

        @Override
        public boolean reportRecovery() {
            return false;
        }

        @Override
        public DetailDesc detailDesc() {
            return new DetailDesc("Instance Mark Down", "redis health check fail and will be pull out from cluster");
        }
    },
    ALERT_SYSTEM_OFF("alert system is turning off", EMAIL_DBA | EMAIL_XPIPE_ADMIN) {
        @Override
        public boolean urgent() {
            return true;
        }

        @Override
        public boolean reportRecovery() {
            return false;
        }

        @Override
        public DetailDesc detailDesc() {
            return new DetailDesc("alert system is turning off", "");
        }
    },
    SENTINEL_AUTO_PROCESS_OFF("sentinel auto process is turning off", EMAIL_DBA | EMAIL_XPIPE_ADMIN) {
        @Override
        public boolean urgent() {
            return true;
        }

        @Override
        public boolean reportRecovery() {
            return false;
        }

        @Override
        public DetailDesc detailDesc() {
            return new DetailDesc("sentinel auto process is turning off", "");
        }
    },
    REPL_BACKLOG_NOT_ACTIVE("repl_backlog_not_active", EMAIL_DBA | EMAIL_XPIPE_ADMIN) {
        @Override
        public boolean urgent() {
            return false;
        }

        @Override
        public boolean reportRecovery() {
            return true;
        }

        @Override
        public DetailDesc detailDesc() {
            return new DetailDesc("Redis Backlog is empty", "Redis depend on Backlog to cache data going to sync,  Backlog missing will take risks of full sync");
        }
    },
    SENTINEL_MONITOR_REDUNDANT_REDIS("sentinel_monitors_redundant_redis", EMAIL_XPIPE_ADMIN) {
        @Override
        public boolean urgent() {
            return false;
        }

        @Override
        public boolean reportRecovery() {
            return false;
        }

        @Override
        public DetailDesc detailDesc() {
            return new DetailDesc("sentinel find redundant redis", "Redis may be not config in XPipe");
        }
    },
    SENTINEL_MONITOR_INCONSIS("sentinel_monitor_incosis", EMAIL_XPIPE_ADMIN) {
        @Override
        public boolean urgent() {
            return false;
        }

        @Override
        public boolean reportRecovery() {
            return false;
        }

        @Override
        public DetailDesc detailDesc() {
            return new DetailDesc("real sentinels inconsistent with config", "");
        }
    },
    INSTANCE_SICK_BUT_DELAY_MARK_DOWN("instance_lag_delay_mark_down", EMAIL_XPIPE_ADMIN | EMAIL_DBA) {
        @Override
        public boolean urgent() {
            return false;
        }

        @Override
        public boolean reportRecovery() {
            return false;
        }

        @Override
        public DetailDesc detailDesc() {
            return new DetailDesc("instance_lag_delay_mark_down(usually in site oversea)", "the redis will not be pull out immediately, but be pull out since keep on down for a long time(usually half/one hour)");
        }
    },
    MIGRATION_SYSTEM_CHECK_OVER_DUE("migration_system_not_checked", EMAIL_XPIPE_ADMIN) {
        @Override
        public boolean urgent() {
            return false;
        }

        @Override
        public boolean reportRecovery() {
            return false;
        }

        @Override
        public DetailDesc detailDesc() {
            return new DetailDesc("DR migration system check fail", "DR migration system has been unavailable for a long time");
        }
    };

    private String simpleDesc;

    private int alertMethod;

    ALERT_TYPE(String simpleDesc, int alertMethod){
        this.simpleDesc = simpleDesc;
        this.alertMethod = alertMethod;
    }

    public String simpleDesc() {
        return simpleDesc;
    }

    public int getAlertMethod() {
        return alertMethod;
    }

    public abstract boolean urgent();

    public abstract boolean reportRecovery();

    public abstract DetailDesc detailDesc();

    public class DetailDesc {

        private String title;

        private String desc;

        public DetailDesc(String title, String desc) {
            this.title = title;
            this.desc = desc;
        }

        public String getTitle() {
            return title;
        }

        public String getDesc() {
            return desc;
        }
    }
}
