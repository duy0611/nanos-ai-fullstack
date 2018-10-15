package ai.nanos.fullstack.domain.enumeration;

/**
 * The AdCampaignPlatformType enumeration.
 */
public enum AdCampaignPlatformType {
    Facebook, Instagram, Google;

	public static AdCampaignPlatformType of(String value) {
		if (Facebook.toString().equalsIgnoreCase(value)) {
    		return Facebook;
    	} else if (Instagram.toString().equalsIgnoreCase(value)) {
    		return Instagram;
    	} else if (Google.toString().equalsIgnoreCase(value)) {
    		return Google;
    	}
    	throw new RuntimeException("AdCampaignPlatformType enum type is not supported for value : " + value);
	}
}
