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
	public static Image[] eriJumpHead1;
	public static Image[] eriJumpFoot1;
	public static Image[] eriJumpHead2;
	public static Image[] eriJumpFoot2;


	static {
		try {
			
			eriStandbyHead = new Image[] { new Image("resources/EriAnimations/Standby/StandbyCabeza1.png", false, Image.FILTER_NEAREST).getScaledCopy(3),
					new Image("resources/EriAnimations/Standby/StandbyCabeza2.png", false, Image.FILTER_NEAREST).getScaledCopy(3),
					new Image("resources/EriAnimations/Standby/StandbyCabeza3.png", false, Image.FILTER_NEAREST).getScaledCopy(3),
					new Image("resources/EriAnimations/Standby/StandbyCabeza4.png", false, Image.FILTER_NEAREST).getScaledCopy(3) };

			eriStandbyFoot = new Image[] { new Image("resources/EriAnimations/Standby/StandbyPiernas1.png", false, Image.FILTER_NEAREST).getScaledCopy(3) };

			eriRunHead = new Image[] { new Image("resources/EriAnimations/Run/RunHead1.png", false, Image.FILTER_NEAREST).getScaledCopy(3),
					new Image("resources/EriAnimations/Run/RunHead2.png", false, Image.FILTER_NEAREST).getScaledCopy(3),
					new Image("resources/EriAnimations/Run/RunHead3.png", false, Image.FILTER_NEAREST).getScaledCopy(3),
					new Image("resources/EriAnimations/Run/RunHead4.png", false, Image.FILTER_NEAREST).getScaledCopy(3),
					new Image("resources/EriAnimations/Run/RunHead5.png", false, Image.FILTER_NEAREST).getScaledCopy(3),
					new Image("resources/EriAnimations/Run/RunHead6.png", false, Image.FILTER_NEAREST).getScaledCopy(3),
					new Image("resources/EriAnimations/Run/RunHead7.png", false, Image.FILTER_NEAREST).getScaledCopy(3),
					new Image("resources/EriAnimations/Run/RunHead8.png", false, Image.FILTER_NEAREST).getScaledCopy(3),
					new Image("resources/EriAnimations/Run/RunHead9.png", false, Image.FILTER_NEAREST).getScaledCopy(3),
					new Image("resources/EriAnimations/Run/RunHead10.png", false, Image.FILTER_NEAREST).getScaledCopy(3),
					new Image("resources/EriAnimations/Run/RunHead11.png", false, Image.FILTER_NEAREST).getScaledCopy(3),
					new Image("resources/EriAnimations/Run/RunHead12.png", false, Image.FILTER_NEAREST).getScaledCopy(3) };

			eriRunFoot1 = new Image[] { new Image("resources/EriAnimations/Run/RunFoot1.png", false, Image.FILTER_NEAREST).getScaledCopy(3),
					new Image("resources/EriAnimations/Run/RunFoot2.png", false, Image.FILTER_NEAREST).getScaledCopy(3),
					new Image("resources/EriAnimations/Run/RunFoot3.png", false, Image.FILTER_NEAREST).getScaledCopy(3),
					new Image("resources/EriAnimations/Run/RunFoot4.png", false, Image.FILTER_NEAREST).getScaledCopy(3) };
			eriStandbyHead = new Image[] { new Image("resources/EriAnimations/Standby/StandbyCabeza1.png", false, Image.FILTER_NEAREST).getScaledCopy(3),
					new Image("resources/EriAnimations/Standby/StandbyCabeza2.png", false, Image.FILTER_NEAREST).getScaledCopy(3),
					new Image("resources/EriAnimations/Standby/StandbyCabeza3.png", false, Image.FILTER_NEAREST).getScaledCopy(3),
					new Image("resources/EriAnimations/Standby/StandbyCabeza4.png", false, Image.FILTER_NEAREST).getScaledCopy(3) };

			eriStandbyFoot = new Image[] { new Image("resources/EriAnimations/Standby/StandbyPiernas1.png", false, Image.FILTER_NEAREST).getScaledCopy(3) };

			eriRunHead = new Image[] { new Image("resources/EriAnimations/Run/RunHead1.png", false, Image.FILTER_NEAREST).getScaledCopy(3),
					new Image("resources/EriAnimations/Run/RunHead2.png", false, Image.FILTER_NEAREST).getScaledCopy(3),
					new Image("resources/EriAnimations/Run/RunHead3.png", false, Image.FILTER_NEAREST).getScaledCopy(3),
					new Image("resources/EriAnimations/Run/RunHead4.png", false, Image.FILTER_NEAREST).getScaledCopy(3),
					new Image("resources/EriAnimations/Run/RunHead5.png", false, Image.FILTER_NEAREST).getScaledCopy(3),
					new Image("resources/EriAnimations/Run/RunHead6.png", false, Image.FILTER_NEAREST).getScaledCopy(3),
					new Image("resources/EriAnimations/Run/RunHead7.png", false, Image.FILTER_NEAREST).getScaledCopy(3),
					new Image("resources/EriAnimations/Run/RunHead8.png", false, Image.FILTER_NEAREST).getScaledCopy(3),
					new Image("resources/EriAnimations/Run/RunHead9.png", false, Image.FILTER_NEAREST).getScaledCopy(3),
					new Image("resources/EriAnimations/Run/RunHead10.png", false, Image.FILTER_NEAREST).getScaledCopy(3),
					new Image("resources/EriAnimations/Run/RunHead11.png", false, Image.FILTER_NEAREST).getScaledCopy(3),
					new Image("resources/EriAnimations/Run/RunHead12.png", false, Image.FILTER_NEAREST).getScaledCopy(3) };

			eriRunFoot1 = new Image[] { new Image("resources/EriAnimations/Run/RunFoot1.png", false, Image.FILTER_NEAREST).getScaledCopy(3),
					new Image("resources/EriAnimations/Run/RunFoot2.png", false, Image.FILTER_NEAREST).getScaledCopy(3),
					new Image("resources/EriAnimations/Run/RunFoot3.png", false, Image.FILTER_NEAREST).getScaledCopy(3),
					new Image("resources/EriAnimations/Run/RunFoot4.png", false, Image.FILTER_NEAREST).getScaledCopy(3) };


		} catch (SlickException e) {
		}
	}

}
