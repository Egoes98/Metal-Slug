package es.deusto.prog3.metalslug.tests.collisions;

import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

public class AnimationImages {

	public static Image[] eriStandbyHead;
	public static Image[] eriStandbyFoot;
	public static Image[] eriRunHead;
	public static Image[] eriRunFoot1;
	public static Image[] eriRunFoot2;
	public static Image[] eriShoot;


	static {
		try {
			eriStandbyHead = new Image[] { new Image("resources/EriAnimations/Standby/StandbyCabeza1.png"),
					new Image("resources/EriAnimations/Standby/StandbyCabeza2.png"),
					new Image("resources/EriAnimations/Standby/StandbyCabeza3.png"),
					new Image("resources/EriAnimations/Standby/StandbyCabeza4.png") };

			eriStandbyFoot = new Image[] { new Image("resources/EriAnimations/Standby/StandbyPiernas1.png") };

			eriRunHead = new Image[] { new Image("resources/EriAnimations/Run/RunHead1.png"),
					new Image("resources/EriAnimations/Run/RunHead2.png"),
					new Image("resources/EriAnimations/Run/RunHead3.png"),
					new Image("resources/EriAnimations/Run/RunHead4.png"),
					new Image("resources/EriAnimations/Run/RunHead5.png"),
					new Image("resources/EriAnimations/Run/RunHead6.png"),
					new Image("resources/EriAnimations/Run/RunHead7.png"),
					new Image("resources/EriAnimations/Run/RunHead8.png"),
					new Image("resources/EriAnimations/Run/RunHead9.png"),
					new Image("resources/EriAnimations/Run/RunHead10.png"),
					new Image("resources/EriAnimations/Run/RunHead11.png"),
					new Image("resources/EriAnimations/Run/RunHead12.png") };

			eriRunFoot1 = new Image[] { new Image("resources/EriAnimations/Run/RunFoot1.png"),
					new Image("resources/EriAnimations/Run/RunFoot2.png"),
					new Image("resources/EriAnimations/Run/RunFoot3.png"),
					new Image("resources/EriAnimations/Run/RunFoot4.png") };

			eriRunFoot2 = new Image[] { new Image("resources/EriAnimations/Run/RunFoot5.png"),
					new Image("resources/EriAnimations/Run/RunFoot6.png"),
					new Image("resources/EriAnimations/Run/RunFoot7.png"),
					new Image("resources/EriAnimations/Run/RunFoot8.png"),
					new Image("resources/EriAnimations/Run/RunFoot9.png"),
					new Image("resources/EriAnimations/Run/RunFoot10.png"),
					new Image("resources/EriAnimations/Run/RunFoot11.png"),
					new Image("resources/EriAnimations/Run/RunFoot12.png"),
					new Image("resources/EriAnimations/Run/RunFoot13.png"),
					new Image("resources/EriAnimations/Run/RunFoot14.png"),
					new Image("resources/EriAnimations/Run/RunFoot15.png"),
					new Image("resources/EriAnimations/Run/RunFoot16.png") };
			
			eriShoot = new Image[] { new Image("resources/EriAnimations/Shoot/Shoot1.png"),
					new Image("resources/EriAnimations/Shoot/Shoot2.png"),
					new Image("resources/EriAnimations/Shoot/Shoot3.png"),
					new Image("resources/EriAnimations/Shoot/Shoot4.png"),
					new Image("resources/EriAnimations/Shoot/Shoot5.png"),
					new Image("resources/EriAnimations/Shoot/Shoot6.png") };
					
			
		} catch (SlickException e) {
		}
	}

}
