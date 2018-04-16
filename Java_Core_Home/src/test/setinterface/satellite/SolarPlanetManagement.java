package test.setinterface.satellite;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import test.setinterface.satellite.HeavenlyBody.BodyTypes;

public class SolarPlanetManagement {
	private static Map<HeavenlyBody.Key, HeavenlyBody> solarSystem = new HashMap<>();
	private static Set<HeavenlyBody> planets = new HashSet<>(); 

	public static void main(String[] args) {
		HeavenlyBody temp = new Planet("Mercury", 88);
        solarSystem.put(temp.getKey(), temp);
        planets.add(temp);

        temp = new Planet("Venus", 225);
        solarSystem.put(temp.getKey(), temp);
        planets.add(temp);

        temp = new Planet("Earth", 365);
        solarSystem.put(temp.getKey(), temp);
        planets.add(temp);

        HeavenlyBody tempMoon = new Moon("Moon", 27);
        solarSystem.put(tempMoon.getKey(), tempMoon);
        temp.addSattelite(tempMoon);

        temp = new Planet("Mars", 687);
        solarSystem.put(temp.getKey(), temp);
        planets.add(temp);

        tempMoon = new Moon("Deimos", 1.3);
        solarSystem.put(tempMoon.getKey(), tempMoon);
        temp.addSattelite(tempMoon); // temp is still Mars

        tempMoon = new Moon("Phobos", 0.3);
        solarSystem.put(tempMoon.getKey(), tempMoon);
        temp.addSattelite(tempMoon); // temp is still Mars

        temp = new Planet("Jupiter", 4332);
        solarSystem.put(temp.getKey(), temp);
        planets.add(temp);

        tempMoon = new Moon("Io", 1.8);
        solarSystem.put(tempMoon.getKey(), tempMoon);
        temp.addSattelite(tempMoon); // temp is still Jupiter

        tempMoon = new Moon("Europa", 3.5);
        solarSystem.put(tempMoon.getKey(), tempMoon);
        temp.addSattelite(tempMoon); // temp is still Jupiter

        tempMoon = new Moon("Ganymede", 7.1);
        solarSystem.put(tempMoon.getKey(), tempMoon);
        temp.addSattelite(tempMoon); // temp is still Jupiter

        tempMoon = new Moon("Callisto", 16.7);
        solarSystem.put(tempMoon.getKey(), tempMoon);
        temp.addSattelite(tempMoon); // temp is still Jupiter

        temp = new Planet("Saturn", 10759);
        solarSystem.put(temp.getKey(), temp);
        planets.add(temp);

        temp = new Planet("Uranus", 30660);
        solarSystem.put(temp.getKey(), temp);
        planets.add(temp);

        temp = new Planet("Neptune", 165);
        solarSystem.put(temp.getKey(), temp);
        planets.add(temp);

        temp = new Planet("Pluto", 248);
        solarSystem.put(temp.getKey(), temp);
        planets.add(temp);
        
        System.out.println("List of Planet:");
        for (HeavenlyBody planet : planets) {
        	System.out.println(planet);
//        	System.out.print("\t" + planet.getName() + "\t");
        }
        
        HeavenlyBody body = solarSystem.get(HeavenlyBody.makeKey("Jupiter", BodyTypes.PLANNET));
        System.out.println("\nMoon of the " + body.getKey() + " are :");
        for (HeavenlyBody moon : body.getSatellites()) {
        	System.out.print("\t" + moon.getKey() + "\t");
        }
        
        Set<HeavenlyBody> moons = new HashSet<>();
        for (HeavenlyBody moon : planets) {
        	moons.addAll(moon.getSatellites());
        }
        
        System.out.println("\nAll moons of all our plannet are : ");
        for(HeavenlyBody moon : moons) {
        	System.out.print("\t" + moon.getKey());
        }
        
        HeavenlyBody pluto = new Planet("Pluto", 824);
        solarSystem.put(pluto.getKey(), pluto);
        planets.add(pluto);
        pluto = new DwarfPlanet("Pluto", 824);
        solarSystem.put(pluto.getKey(), pluto);
        planets.add(pluto);
        
        System.out.println("List of Planet:");
        for (HeavenlyBody planet : planets) {
        	System.out.println(planet);
        }
        
        HeavenlyBody earth1 = new Planet("Earth", 365);
        HeavenlyBody earth2 = new Planet("Earth", 365);
        System.out.println("Earth 1 compare to Earth 2 is " + earth1.equals(earth2));
        System.out.println("Earth 2 compare to Earth 1 is " + earth2.equals(earth1));
        System.out.println("Pluto compare to Earth is " + earth1.equals(pluto));
        System.out.println("Earth compare to Pluto is " + pluto.equals(earth1));        
        
        System.out.println(pluto.makeKey("Pluto", HeavenlyBody.BodyTypes.PLANNET));
        System.out.println(pluto.makeKey("Pluto", HeavenlyBody.BodyTypes.DWARF_PLANNET));
        
	}

}
