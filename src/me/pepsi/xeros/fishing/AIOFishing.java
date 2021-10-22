package me.pepsi.xeros.fishing;

import simple.api.script.Category;
import simple.api.script.ScriptManifest;
import simple.api.script.interfaces.SimplePaintable;
import simple.api.wrappers.SimpleGameObject;
import simple.api.wrappers.SimpleNpc;
import simple.api.actions.SimpleNpcActions;
import simple.api.actions.SimpleObjectActions;
import simple.api.coords.WorldArea;
import simple.api.coords.WorldPoint;
import simple.api.events.ChatMessageEvent;
import simple.api.filters.SimpleSkills;
import simple.api.listeners.SimpleMessageListener;
import simple.api.script.Script;

import java.awt.*;


@ScriptManifest(author = "Pepsiplaya", name = "Progressive Fisher", category = Category.FISHING, version = "1.1D",
        description = "Fishing On Skilling Island Till 99. Make sure to start the script with an empty inventory and make sure you're at Skilling Island", discord = "Pepsiplaya#6988", servers = { "Xeros" })

public class AIOFishing extends Script implements SimplePaintable, SimpleMessageListener {
	
    public String status;
    private long startLevel;
	private long startExp;
    public long startTime;
    
    public static final WorldArea HOME_AREA = new WorldArea(
            new WorldPoint(3072, 3521, 0), new WorldPoint(3072, 3464, 0),
            new WorldPoint(3137, 3474, 0), new WorldPoint(3137, 3521, 0));
    
    public static final WorldArea SKILLING_AREA = new WorldArea(
            new WorldPoint(3700, 3400, 0),
            new WorldPoint(3900, 3600, 0));
    
    public final WorldPoint shrimp = new WorldPoint(3800, 3560, 0);
    public final WorldPoint trout = new WorldPoint(3798, 3560, 0);
    public final WorldPoint lobster = new WorldPoint(3796, 3560, 0);
    public final WorldPoint shark = new WorldPoint(3794, 3560, 0);
    
    @Override
    public boolean onExecute() {
    	startLevel = ctx.skills.getRealLevel(SimpleSkills.Skill.FISHING);
    	startExp = ctx.skills.getExperience(SimpleSkills.Skill.FISHING);
        startTime = System.currentTimeMillis();
        return true;
    }
    
    void bankShrimp() {
		SimpleGameObject bank = (SimpleGameObject) ctx.objects.populate().filter(20325).nearest().next();
		SimpleNpc shrimpFishing = (SimpleNpc) ctx.npcs.populate().filter(3913).nearest().next();
		if (bank != null && ctx.inventory.populate().filter(303).isEmpty()) {
			bank.interact(SimpleObjectActions.FIRST);
			status = "Banking";
			ctx.onCondition(() -> ctx.bank.bankOpen(), 10000);
			ctx.sleep(250);
			ctx.bank.depositInventory();
			ctx.sleep(250);
			ctx.bank.withdraw(303, 1);
			ctx.bank.closeBank();
		} else {
			bank.interact(SimpleObjectActions.FIRST);
			status = "Banking";
			ctx.onCondition(() -> ctx.bank.bankOpen(), 10000);
			ctx.sleep(250);
			ctx.bank.depositAllExcept(303);
			ctx.bank.closeBank();
		}
		
		if (shrimpFishing != null && !ctx.players.getLocal().isAnimating()) { 
			status = "Fishing";
			ctx.menuActions.interact(shrimpFishing, SimpleNpcActions.NET);
			ctx.onCondition(() -> !ctx.players.getLocal().isAnimating(), 250, 14);
		}
	}
    
    void bankTrout() {
		SimpleGameObject bank = (SimpleGameObject) ctx.objects.populate().filter(20325).nearest().next();
		SimpleNpc troutFishing = ctx.npcs.populate().filter(3417).nearest().next();
		
		if (bank != null && (ctx.inventory.populate().filter(309).isEmpty() || ctx.inventory.populate().filter(314).isEmpty())) {
			bank.interact(SimpleObjectActions.FIRST);
			status = "Banking";
			ctx.onCondition(() -> ctx.bank.bankOpen(), 10000);
			ctx.sleep(250);
			ctx.bank.depositInventory();
			ctx.sleep(250);
			ctx.bank.withdraw(309, 1);
			ctx.sleep(250);
			ctx.bank.withdraw(314, 123);
			ctx.bank.closeBank();
		} else {
			bank.interact(SimpleObjectActions.FIRST);
			status = "Banking";
			ctx.onCondition(() -> ctx.bank.bankOpen(), 10000);
			ctx.sleep(250);
			ctx.bank.depositAllExcept(309, 314);
			ctx.bank.closeBank();
		}
		
		if (troutFishing != null && !ctx.players.getLocal().isAnimating()) {
			status = "Fishing";
			ctx.menuActions.interact(troutFishing, SimpleNpcActions.INTERACT);
			ctx.onCondition(() -> !ctx.players.getLocal().isAnimating(), 250, 14);
		}
	}
    
    void bankLobster() {
		SimpleGameObject bank = (SimpleGameObject) ctx.objects.populate().filter(20325).nearest().next();
		SimpleNpc lobsterFishing = (SimpleNpc) ctx.npcs.populate().filter(3657).nearest().next();
		
		if (bank != null && ctx.inventory.populate().filter(301).isEmpty()) {
			bank.interact(SimpleObjectActions.FIRST);
			status = "Banking";
			ctx.onCondition(() -> ctx.bank.bankOpen(), 10000);
			ctx.sleep(250);
			ctx.bank.depositInventory();
			ctx.sleep(250);
			ctx.bank.withdraw(301, 1);
			ctx.bank.closeBank();
		} else {
			bank.interact(SimpleObjectActions.FIRST);
			status = "Banking";
			ctx.onCondition(() -> ctx.bank.bankOpen(), 10000);
			ctx.sleep(250);
			ctx.bank.depositAllExcept(301);
			ctx.bank.closeBank();
		}
		
		if (lobsterFishing != null && !ctx.players.getLocal().isAnimating()) {
			status = "Fishing";
			ctx.menuActions.interact(lobsterFishing, SimpleNpcActions.CAGE);
			ctx.onCondition(() -> !ctx.players.getLocal().isAnimating(), 250, 14);
		}
	}
    
    void bankSwordfish() {
		SimpleGameObject bank = (SimpleGameObject) ctx.objects.populate().filter(20325).nearest().next();
		SimpleNpc swordfishFishing = (SimpleNpc) ctx.npcs.populate().filter(3657).nearest().next();
		
		if (bank != null && ctx.inventory.populate().filter(311).isEmpty()) {
			bank.interact(SimpleObjectActions.FIRST);
			status = "Banking";
			ctx.onCondition(() -> ctx.bank.bankOpen(), 10000);
			ctx.sleep(250);
			ctx.bank.depositInventory();
			ctx.sleep(250);
			ctx.bank.withdraw(311, 1);
			ctx.bank.closeBank();
		} else {
			bank.interact(SimpleObjectActions.FIRST);
			status = "Banking";
			ctx.onCondition(() -> ctx.bank.bankOpen(), 10000);
			ctx.sleep(250);
			ctx.bank.depositAllExcept(311);
			ctx.bank.closeBank();
		}
		
		if (swordfishFishing != null && !ctx.players.getLocal().isAnimating()) {
			status = "Fishing";
			ctx.menuActions.interact(swordfishFishing, SimpleNpcActions.HARPOON);
			ctx.onCondition(() -> !ctx.players.getLocal().isAnimating(), 250, 14);
		}
	}
    
    void bankMonkfish() {
		SimpleGameObject bank = (SimpleGameObject) ctx.objects.populate().filter(20325).nearest().next();
		SimpleNpc monkfishFishing = (SimpleNpc) ctx.npcs.populate().filter(1520).nearest().next();
		
		if (bank != null && ctx.inventory.populate().filter(303).isEmpty()) {
			bank.interact(SimpleObjectActions.FIRST);
			status = "Banking";
			ctx.onCondition(() -> ctx.bank.bankOpen(), 10000);
			ctx.sleep(250);
			ctx.bank.depositInventory();
			ctx.sleep(250);
			ctx.bank.withdraw(303, 1);
			ctx.bank.closeBank();
		} else {
			bank.interact(SimpleObjectActions.FIRST);
			status = "Banking";
			ctx.onCondition(() -> ctx.bank.bankOpen(), 10000);
			ctx.sleep(250);
			ctx.bank.depositAllExcept(303);
			ctx.bank.closeBank();
		}
		
		if (monkfishFishing != null && !ctx.players.getLocal().isAnimating()) {
			status = "Fishing";
			ctx.menuActions.interact(monkfishFishing, SimpleNpcActions.NET);
			ctx.onCondition(() -> !ctx.players.getLocal().isAnimating(), 250, 14);
		}
	}
    
    void bankShark() {
		SimpleGameObject bank = (SimpleGameObject) ctx.objects.populate().filter(20325).nearest().next();
		SimpleNpc sharkFishing = (SimpleNpc) ctx.npcs.populate().filter(1520).nearest().next();
		
		if (bank != null && ctx.inventory.populate().filter(311).isEmpty()) {
			bank.interact(SimpleObjectActions.FIRST);
			status = "Banking";
			ctx.onCondition(() -> ctx.bank.bankOpen(), 10000);
			ctx.sleep(250);
			ctx.bank.depositInventory();
			ctx.sleep(250);
			ctx.bank.withdraw(311, 1);
			ctx.bank.closeBank();
		} else {
			bank.interact(SimpleObjectActions.FIRST);
			status = "Banking";
			ctx.onCondition(() -> ctx.bank.bankOpen(), 10000);
			ctx.sleep(250);
			ctx.bank.depositAllExcept(311);
			ctx.bank.closeBank();
		}
		
		if (sharkFishing != null && !ctx.players.getLocal().isAnimating()) {
			status = "Fishing";
			ctx.menuActions.interact(sharkFishing, SimpleNpcActions.HARPOON);
			ctx.onCondition(() -> !ctx.players.getLocal().isAnimating(), 250, 14);
		}
	}

    @Override
    public void onProcess() {
    	
    	if (!SKILLING_AREA.containsPoint(ctx.players.getLocal())) {
    		return;
        }
    	
    	if (SKILLING_AREA.containsPoint(ctx.players.getLocal())) {
            if (ctx.skills.getRealLevel(SimpleSkills.Skill.FISHING) < 20) {
            	if (ctx.inventory.populate().filterContains("Small").isEmpty() || ctx.inventory.inventoryFull()) {
            		bankShrimp();
            	}
            	SimpleNpc shrimpFishing = (SimpleNpc) ctx.npcs.populate().filter(3913).nearest().next();
            	if (!ctx.inventory.populate().filterContains("Small").isEmpty() && !ctx.inventory.inventoryFull() && ctx.players.getLocal().getAnimation() == -1) {
            		if (shrimpFishing != null) { 
            			status = "Fishing";
            			ctx.menuActions.interact(shrimpFishing, SimpleNpcActions.NET);
            			ctx.onCondition(() -> !ctx.players.getLocal().isAnimating(), 250, 14);
                	}
            	}
            }
            
            if (ctx.skills.getRealLevel(SimpleSkills.Skill.FISHING) >= 20 && ctx.skills.getRealLevel(SimpleSkills.Skill.FISHING) < 40) {
            	if ((ctx.inventory.populate().filterContains("Fly").isEmpty() || ctx.inventory.populate().filter("Feather").isEmpty()) || ctx.inventory.inventoryFull()) {
            		bankTrout();
            	}
        		SimpleNpc troutFishing = ctx.npcs.populate().filter(3417).nearest().next();
            	if (!ctx.inventory.populate().filterContains("Fly").isEmpty() && !ctx.inventory.inventoryFull() && ctx.players.getLocal().getAnimation() == -1) {
            		if (troutFishing != null) { 
            			status = "Fishing";
            			ctx.menuActions.interact(troutFishing, SimpleNpcActions.INTERACT);
            			ctx.onCondition(() -> !ctx.players.getLocal().isAnimating(), 250, 14);
            		}
            	}
            }
            
            if (ctx.skills.getRealLevel(SimpleSkills.Skill.FISHING) >= 40 && ctx.skills.getRealLevel(SimpleSkills.Skill.FISHING) < 50) {
            	if (ctx.inventory.populate().filter(301).isEmpty() || ctx.inventory.inventoryFull()) {
            		bankLobster();
            	}
            	SimpleNpc lobsterFishing = (SimpleNpc) ctx.npcs.populate().filter(3657).nearest().next();
            	if (!ctx.inventory.populate().filter(301).isEmpty() && !ctx.inventory.inventoryFull() && ctx.players.getLocal().getAnimation() == -1) {
            		if (lobsterFishing != null) { 
            			status = "Fishing";
            			ctx.menuActions.interact(lobsterFishing, SimpleNpcActions.CAGE);
            			ctx.onCondition(() -> !ctx.players.getLocal().isAnimating(), 250, 14);
            		}
            	}
            }
            
            if (ctx.skills.getRealLevel(SimpleSkills.Skill.FISHING) >= 50 && ctx.skills.getRealLevel(SimpleSkills.Skill.FISHING) < 62) {
            	if (ctx.inventory.populate().filter("Harpoon").isEmpty() || ctx.inventory.inventoryFull()) {
            		bankSwordfish();
            	}
            	SimpleNpc swordfishFishing = (SimpleNpc) ctx.npcs.populate().filter(3657).nearest().next();
            	if (!ctx.inventory.populate().filter("Harpoon").isEmpty() && !ctx.inventory.inventoryFull() && ctx.players.getLocal().getAnimation() == -1) {
            		if (swordfishFishing != null) { 
            			status = "Fishing";
            			ctx.menuActions.interact(swordfishFishing, SimpleNpcActions.HARPOON);
            			ctx.onCondition(() -> !ctx.players.getLocal().isAnimating(), 250, 14);
            		}
            	}
            }
            
            if (ctx.skills.getRealLevel(SimpleSkills.Skill.FISHING) >= 62 && ctx.skills.getRealLevel(SimpleSkills.Skill.FISHING) < 76) {
            	if (ctx.inventory.populate().filterContains("Small").isEmpty() || ctx.inventory.inventoryFull()) {
            		bankMonkfish();
            	}
            	SimpleNpc monkfishFishing = (SimpleNpc) ctx.npcs.populate().filter(1520).nearest().next();
            	if (!ctx.inventory.populate().filterContains("Small").isEmpty() && !ctx.inventory.inventoryFull() && ctx.players.getLocal().getAnimation() == -1) {
            		if (monkfishFishing != null) { 
            			status = "Fishing";
            			ctx.menuActions.interact(monkfishFishing, SimpleNpcActions.NET);
            			ctx.onCondition(() -> !ctx.players.getLocal().isAnimating(), 250, 14);
            		}
            	}
            }
            
            if (ctx.skills.getRealLevel(SimpleSkills.Skill.FISHING) >= 76) {
            	if (ctx.inventory.populate().filter("Harpoon").isEmpty() || ctx.inventory.inventoryFull()) {
            		bankShark();
            	}
            	SimpleNpc sharkFishing = (SimpleNpc) ctx.npcs.populate().filter(1520).nearest().next();
            	if (!ctx.inventory.populate().filter("Harpoon").isEmpty() && !ctx.inventory.inventoryFull() && ctx.players.getLocal().getAnimation() == -1) {
            		if (sharkFishing != null) { 
            			status = "Fishing";
            			ctx.menuActions.interact(sharkFishing, SimpleNpcActions.HARPOON);
            			ctx.onCondition(() -> !ctx.players.getLocal().isAnimating(), 250, 14);
            		}
            	}
            }
        }
    }

    @Override
    public void onTerminate() {
    	
    }
    
	public final String format(final long t) {
	    long s = t;
	    return String.format("%", s);
	}
    
	public final String formatValue(final long l) {
	    return (l > 1_000_000) ? String.format("%.2fm", ((double) l / 1_000_000))
	           : (l > 1000) ? String.format("%.1fk", ((double) l / 1000)) 
	           : l + "";
	}
	
    private final Color color1 = new Color(255, 255, 255);
    private final Font font1 = new Font("Arial", 1, 10);
    private final Color color2 = new Color(29, 3, 3, 94);
    private final Color color3 = new Color(0, 0, 0);
    private final BasicStroke stroke1 = new BasicStroke(1);
    
	@Override
    public void onPaint(Graphics2D g) {
		
		final int cachedExp = ctx.skills.getRealLevel(SimpleSkills.Skill.FISHING);
		int catches = 0;
		final long fishingLevels = ctx.skills.getRealLevel(SimpleSkills.Skill.FISHING) - startLevel;
		final long fishingExp = ctx.skills.getExperience(SimpleSkills.Skill.FISHING) - startExp;
		
		if (cachedExp != ctx.skills.getRealLevel(SimpleSkills.Skill.FISHING)) { 
			catches = catches + 1;
		}
		
		 g.setColor(color2);
	     g.fillRect(7, 273, 155, 60);
	     g.setColor(color3);
	     g.setStroke(stroke1);
	     g.drawRect(7, 273, 155, 60);
	     g.setFont(font1);
	     g.setColor(color1);
	     g.drawString("Time Running: " + ctx.paint.formatTime(System.currentTimeMillis() - startTime), 10, 287);
	     g.drawString("Exp / Per Hour: " + formatValue(fishingExp) + " / " + formatValue(ctx.paint.valuePerHour((int) fishingExp, startTime)), 10, 300);
	     g.drawString("Levels / Per Hour: " + fishingLevels + " / " + formatValue(ctx.paint.valuePerHour((int) fishingLevels, startTime)), 10, 313);
	     g.drawString("Status: " + status, 10, 326);
    }
	
    public void onChatMessage(ChatMessageEvent event) {
    	
    }
    
}