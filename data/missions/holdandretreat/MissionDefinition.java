package data.missions.holdandretreat;

import com.fs.starfarer.api.combat.BattleObjectiveAPI;
import com.fs.starfarer.api.fleet.FleetGoal;
import com.fs.starfarer.api.fleet.FleetMemberType;
import com.fs.starfarer.api.mission.FleetSide;
import com.fs.starfarer.api.mission.MissionDefinitionAPI;
import com.fs.starfarer.api.mission.MissionDefinitionPlugin;

public class MissionDefinition implements MissionDefinitionPlugin {

	public void defineMission(MissionDefinitionAPI api) {

		// Set up the fleets so we can add ships and fighter wings to them.
		// In this scenario, the fleets are attacking each other, but
		// in other scenarios, a fleet may be defending or trying to escape
		api.initFleet(FleetSide.PLAYER, "RNS", FleetGoal.ATTACK, false);
		api.initFleet(FleetSide.ENEMY, "ISS", FleetGoal.ATTACK, true);

		// Set a small blurb for each fleet that shows up on the mission detail and
		// mission results screens to identify each side.
		api.setFleetTagline(FleetSide.PLAYER, "Junk Pirates mobile detachment and the RNS Clasp");
		api.setFleetTagline(FleetSide.ENEMY, "Tri-Tachyon assault force");
		
		// These show up as items in the bulleted list under 
		// "Tactical Objectives" on the mission detail screen
		api.addBriefingItem("Attack the intruders, in the name of the King!");
		api.addBriefingItem("They can't gain be allowed control of the station!");
		
		// Set up the player's fleet.  Variant names come from the
		// files in data/variants and data/variants/fighters
		api.addToFleet(FleetSide.PLAYER, "junk_pirates_orca_Standard", FleetMemberType.SHIP, "RNS Clasp", true);
		api.addToFleet(FleetSide.PLAYER, "junk_pirates_clam_Standard", FleetMemberType.SHIP, false);
		api.addToFleet(FleetSide.PLAYER, "junk_pirates_clam_CS", FleetMemberType.SHIP, false);
		api.addToFleet(FleetSide.PLAYER, "junk_pirates_clam_CS", FleetMemberType.SHIP, false);
		api.addToFleet(FleetSide.PLAYER, "junk_pirates_sickle_Strike", FleetMemberType.SHIP, false);
		api.addToFleet(FleetSide.PLAYER, "junk_pirates_sickle_Pointdefense", FleetMemberType.SHIP, false);
		api.addToFleet(FleetSide.PLAYER, "junk_pirates_sickle_Standard", FleetMemberType.SHIP, false);
		api.addToFleet(FleetSide.PLAYER, "junk_pirates_boxer_Fighter", FleetMemberType.SHIP, false);
		api.addToFleet(FleetSide.PLAYER, "junk_pirates_boxenstein_Support", FleetMemberType.SHIP, false);
		api.addToFleet(FleetSide.PLAYER, "junk_pirates_sickle_Standard", FleetMemberType.SHIP, false);
		api.addToFleet(FleetSide.PLAYER, "junk_pirates_hammer_Assault", FleetMemberType.SHIP, false);
		api.addToFleet(FleetSide.PLAYER, "junk_pirates_spike_wing", FleetMemberType.FIGHTER_WING, false);
		api.addToFleet(FleetSide.PLAYER, "junk_pirates_spike_wing", FleetMemberType.FIGHTER_WING, false);
		api.addToFleet(FleetSide.PLAYER, "junk_pirates_cleat_wing", FleetMemberType.FIGHTER_WING, false);
		api.addToFleet(FleetSide.PLAYER, "junk_pirates_cleat_wing", FleetMemberType.FIGHTER_WING, false);
		
		
		// Set up the enemy fleet.
		// An elite Tri-Tachyon detachment.
		api.addToFleet(FleetSide.ENEMY, "wasp_wing", FleetMemberType.FIGHTER_WING, false);
		api.addToFleet(FleetSide.ENEMY, "wasp_wing", FleetMemberType.FIGHTER_WING, false);
		api.addToFleet(FleetSide.ENEMY, "gemini_Standard", FleetMemberType.SHIP, false);
		api.addToFleet(FleetSide.ENEMY, "hammerhead_Balanced", FleetMemberType.SHIP, false);
		api.addToFleet(FleetSide.ENEMY, "hammerhead_Elite", FleetMemberType.SHIP, false);		
		api.addToFleet(FleetSide.ENEMY, "longbow_wing", FleetMemberType.FIGHTER_WING, false);
		api.addToFleet(FleetSide.ENEMY, "longbow_wing", FleetMemberType.FIGHTER_WING, false);
		api.addToFleet(FleetSide.ENEMY, "tempest_Attack", FleetMemberType.SHIP, false);
		api.addToFleet(FleetSide.ENEMY, "tempest_Attack", FleetMemberType.SHIP, false);
		api.addToFleet(FleetSide.ENEMY, "tempest_Attack", FleetMemberType.SHIP, false);
		api.addToFleet(FleetSide.ENEMY, "vigilance_FS", FleetMemberType.SHIP, false);
		api.addToFleet(FleetSide.ENEMY, "vigilance_FS", FleetMemberType.SHIP, false);
		api.addToFleet(FleetSide.ENEMY, "medusa_Attack", FleetMemberType.SHIP, false);
		api.addToFleet(FleetSide.ENEMY, "wolf_Strike", FleetMemberType.SHIP, false);
		api.addToFleet(FleetSide.ENEMY, "wolf_Strike", FleetMemberType.SHIP, false);
		
		// Set up the map.
		// 12000x8000 is actually somewhat small, making for a faster-paced mission.
		float width = 14000f;
		float height = 14000f;
		api.initMap((float)-width/2f, (float)width/2f, (float)-height/2f, (float)height/2f);
		
		float minX = -width/2;
		float minY = -height/2;
		
		// And a few random ones to spice up the playing field.
		// A similar approach can be used to randomize everything
		// else, including fleet composition.
		for (int i = 0; i < 6; i++) {
			float x = (float) Math.random() * width - width/2;
			float y = (float) Math.random() * height - height/2;
			float radius = 80f + (float) Math.random() * 100f; 
			api.addNebula(x, y, radius);
		}
		
		// Add objectives. These can be captured by each side
		// and provide stat bonuses and extra command points to
		// bring in reinforcements.
		// Reinforcements only matter for large fleets - in this
		// case, assuming a 100 command point battle size,
		// both fleets will be able to deploy fully right away.
		api.addObjective(minX + width * 0.55f, minY + height * 0.4f, 
						 "sensor_array", BattleObjectiveAPI.Importance.NORMAL);
		api.addObjective(minX + width * 0.75f, minY + height * 0.3f, 
						 "nav_buoy", BattleObjectiveAPI.Importance.NORMAL);
		api.addObjective(minX + width * 0.25f, minY + height * 0.7f, 
						 "comm_relay", BattleObjectiveAPI.Importance.NORMAL);
		
		// Add an asteroid field going diagonally across the
		// battlefield, 2000 pixels wide, with a maximum of 
		// 100 asteroids in it.
		// 20-70 is the range of asteroid speeds.
		// api.addAsteroidField(minX, minY, 45, 3000f,
		//						20f, 70f, 200);
		
		// Add some planets.  These are defined in data/config/planets.json.
		// api.addPlanet(minX + width * 0.75f, minY + height * 0.75f, 300f, "cryovolcanic", 300f);
	}

}
