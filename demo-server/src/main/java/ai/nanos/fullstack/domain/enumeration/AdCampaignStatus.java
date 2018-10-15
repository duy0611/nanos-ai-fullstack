package ai.nanos.fullstack.domain.enumeration;

/**
 * The AdCampaignStatus enumeration.
 */
public enum AdCampaignStatus {
    Scheduled, Delivering, Ended;
    
    public static AdCampaignStatus of(String value) {
    	if (Scheduled.toString().equalsIgnoreCase(value)) {
    		return Scheduled;
    	} else if (Delivering.toString().equalsIgnoreCase(value)) {
    		return Delivering;
    	} else if (Ended.toString().equalsIgnoreCase(value)) {
    		return Ended;
    	}
    	throw new RuntimeException("AdCampaignStatus enum type is not supported for value : " + value);
    }
}
