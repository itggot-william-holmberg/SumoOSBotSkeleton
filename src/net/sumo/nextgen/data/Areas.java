package net.sumo.nextgen.data;

import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

import org.osbot.rs07.api.map.Area;
import org.osbot.rs07.api.map.Position;

public class Areas {

	public static Area SEAGULL_AREA = new Area(new Position[] {
			new Position(3044, 3191, 0),
			new Position(3024, 3192, 0),
			new Position(3017, 3201, 0),
			new Position(3014, 3206, 0),
			new Position(3016, 3210, 0),
			new Position(3033, 3212, 0),
			new Position(3055, 3207, 0)
	});
	public static Area CASTLE_WARS_BANK = new Area(new Position[] {
			new Position(2449, 3081, 0),
			new Position(2430, 3081, 0),
			new Position(2431, 3103, 0),
			new Position(2451, 3104, 0)
	});
	
	public static Area WHOLE_CRAFTING_GUILD =  new Area(new Position[] {
			new Position(2906, 3304, 0),
			new Position(2954, 3300, 0),
			new Position(2957, 3261, 0),
			new Position(2901, 3263, 0)
	});
	public static final Area CRAFTING_GUILD = new Area(2928,3293,2943,3276);
	public static final Area CRAFTING_GUILD_GOLD_ORE = new Area(2943,3276,2937,3284);

	public static final Position[] CRAFTING_GUILD_GOLD_ORE_SPOTS = {new Position(2939,3276,0), new Position(2941,3276,0), new Position(2942,3276,0),
																	new Position(2943,3279,0), new Position(2943,3280,0), new Position(2938,3278,0),
																	new Position(2938,3280,0)};

	public static final Area GRAND_EXCHANGE = new Area(3160,3484,3169,3492);

	public static Area VARROCK_AREA = new Area(
			new Position[] { new Position(3287, 3377, 0), new Position(3153, 3378, 0), new Position(3109, 3467, 0),
					new Position(3110, 3488, 0), new Position(3120, 3496, 0), new Position(3129, 3498, 0),
					new Position(3134, 3513, 0), new Position(3140, 3521, 0), new Position(3185, 3522, 0),
					new Position(3251, 3522, 0), new Position(3251, 3508, 0), new Position(3251, 3500, 0),
					new Position(3254, 3494, 0), new Position(3259, 3493, 0), new Position(3261, 3488, 0),
					new Position(3261, 3478, 0), new Position(3261, 3472, 0), new Position(3264, 3465, 0),
					new Position(3268, 3462, 0), new Position(3269, 3456, 0), new Position(3269, 3446, 0),
					new Position(3269, 3437, 0), new Position(3269, 3433, 0), new Position(3271, 3434, 0) });

	public static Area VARROCK_EAST_MINE = new Area(new Position[] { new Position(3294, 3358, 0),
			new Position(3292, 3357, 0), new Position(3289, 3359, 0), new Position(3285, 3360, 0),
			new Position(3279, 3359, 0), new Position(3279, 3362, 0), new Position(3281, 3364, 0),
			new Position(3282, 3366, 0), new Position(3280, 3373, 0), new Position(3284, 3375, 0),
			new Position(3291, 3374, 0), new Position(3290, 3369, 0), new Position(3291, 3365, 0) });
	public static Area RIMMINGTON_MINE = new Area(new Position[] {
			new Position(2965, 3245, 0),
			new Position(2970, 3249, 0),
			new Position(2978, 3250, 0),
			new Position(2984, 3250, 0),
			new Position(2989, 3245, 0),
			new Position(2988, 3235, 0),
			new Position(2985, 3229, 0),
			new Position(2978, 3228, 0),
			new Position(2971, 3230, 0),
			new Position(2967, 3235, 0),
			new Position(2963, 3240, 0)
	});
	
	public static Area PORT_SARIM_DEPOSIT_AREA = new Area(new Position[] {
			new Position(3052, 3232, 0),
			new Position(3040, 3232, 0),
			new Position(3042, 3237, 0),
			new Position(3054, 3238, 0)
	});
	public static Position VARROCK_EAST_MINE_BEST_IRON_SPOT = new Position(3286, 3368, 0);
	public static Position RIMMINGTON_MINE_BEST_IRON_SPOT = new Position(2982,3233, 0);
	public static Position RIMMINGTON_MINE_BEST_COPPER_SPOT = new Position(2975,3247, 0);
	public static Position[] RIMMINGTON_IRON_ORE_SPOTS = {new Position(2982,3234,0),new Position(2981,3233,0)};
	public static Position[] RIMMINGTON_COPPER_ORE_SPOTS = {new Position(2976,3247,0)};

	public static Position[] VARROCK_EAST_MINE_IRON_ORE_SPOTS = {new Position(3286,3369,0),new Position(3285,3368,0)};
	public static Area SEERS_YEWS_AREA = new Area(new Position[] {
			new Position(2765, 3425, 0),
			new Position(2757, 3425, 0),
			new Position(2753, 3428, 0),
			new Position(2751, 3430, 0),
			new Position(2751, 3435, 0),
			new Position(2754, 3436, 0),
			new Position(2760, 3436, 0),
			new Position(2767, 3437, 0)
	});
	
	
	public static Area NORMAL_TREE_LUMBRIDGE = new Area(new Position[] {
			new Position(3197, 3219, 0),
			new Position(3153, 3223, 0),
			new Position(3154, 3238, 0),
			new Position(3205, 3249, 0)
	});
	public static Area WILLOW_TREE_LUMBRIDGE_AREA = new Area(3182, 3268, 3175, 3277);
	
	public static Area WILLOW_TREE_DRAYNOR_AREA = new Area(new Position[] {
			new Position(3082, 3240, 0),
			new Position(3095, 3239, 0),
			new Position(3095, 3227, 0),
			new Position(3085, 3226, 0)
	});
	
	public static Area MAPLE_TREE_CAMMY = new Area(new Position[] {
			new Position(2716, 3499, 0),
			new Position(2715, 3506, 0),
			new Position(2720, 3508, 0),
			new Position(2733, 3504, 0),
			new Position(2734, 3498, 0)
	});
	
	public static Area YEW_TREE_SEERS = new Area(new Position[] {
			new Position(2752, 3425, 0),
			new Position(2751, 3436, 0),
			new Position(2769, 3439, 0),
			new Position(2771, 3424, 0)
	});
	public static Area TREE_AREA_LUMBRIDGE = new Area(
			new Position[] { new Position(3201, 3196, 0), new Position(3200, 3234, 0), new Position(3213, 3241, 0),
					new Position(3216, 3253, 0), new Position(3197, 3257, 0), new Position(3191, 3258, 0),
					new Position(3190, 3263, 0), new Position(3182, 3270, 0), new Position(3164, 3261, 0),
					new Position(3136, 3263, 0), new Position(3132, 3214, 0) });

	public static Area OAK_TREE_LUMBRIDGE_AREA = new Area(new Position[] { new Position(3209, 3237, 0),
			new Position(3207, 3238, 0), new Position(3203, 3236, 0), new Position(3201, 3234, 0),
			new Position(3196, 3240, 0), new Position(3193, 3249, 0), new Position(3200, 3252, 0),
			new Position(3205, 3253, 0), new Position(3206, 3249, 0), new Position(3209, 3242, 0) });
	
	}
