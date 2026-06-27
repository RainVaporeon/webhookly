package me.micartey.webhookly.features;

import me.micartey.webhookly.JSONObject;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class AllowedMentions {

    public static final AllowedMentions PARSE = new AllowedMentions("parse");
    public static final AllowedMentions USERS = new AllowedMentions("users");
    public static final AllowedMentions ROLES = new AllowedMentions("roles");

    protected final String type;
    protected boolean extended = false;
    protected final List<String> allowlist;

    private AllowedMentions(String type) {
        this.type = type;
        this.allowlist = new ArrayList<>();
    }

    public StaticBuilder extend() {
        StaticBuilder sb = new StaticBuilder(this.type);
        sb.extended = true;
        return sb;
    }

    public static final class StaticBuilder extends AllowedMentions {

        private StaticBuilder(String type) {
            super(type);
        }

        public StaticBuilder allowedMentions(String... allow) {
            if (!extended) {
                throw new IllegalStateException("call extend() before modifying");
            }
            super.allowlist.clear();
            super.allowlist.addAll(Arrays.asList(allow));
            return this;
        }
    }

    public JSONObject toJson() {
        JSONObject o = new JSONObject();
        o.put(this.type, this.allowlist.toArray());
        return o;
    }

}
