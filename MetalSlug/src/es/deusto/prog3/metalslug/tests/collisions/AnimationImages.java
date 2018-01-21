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
	public static Image[] eriJumpFoot1;
	public static Image[] eriJumpFoot2;
	public static Image[] eriJumpHead1;
	public static Image[] eriJumpHead2;
	
	public static Image[] soldierWalk;
	public static Image[] soldierShoot;

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

			eriRunFoot2 = new Image[] { new Image("resources/EriAnimations/Run/RunFoot5.png", false, Image.FILTER_NEAREST).getScaledCopy(3),
					new Image("resources/EriAnimations/Run/RunFoot6.png", false, Image.FILTER_NEAREST).getScaledCopy(3),
					new Image("resources/EriAnimations/Run/RunFoot7.png", false, Image.FILTER_NEAREST).getScaledCopy(3),
					new Image("resources/EriAnimations/Run/RunFoot8.png", false, Image.FILTER_NEAREST).getScaledCopy(3),
					new Image("resources/EriAnimations/Run/RunFoot9.png", false, Image.FILTER_NEAREST).getScaledCopy(3),
					new Image("resources/EriAnimations/Run/RunFoot10.png", false, Image.FILTER_NEAREST).getScaledCopy(3),
					new Image("resources/EriAnimations/Run/RunFoot11.png", false, Image.FILTER_NEAREST).getScaledCopy(3),
					new Image("resources/EriAnimations/Run/RunFoot12.png", false, Image.FILTER_NEAREST).getScaledCopy(3),
					new Image("resources/EriAnimations/Run/RunFoot13.png", false, Image.FILTER_NEAREST).getScaledCopy(3),
					new Image("resources/EriAnimations/Run/RunFoot14.png", false, Image.FILTER_NEAREST).getScaledCopy(3),
					new Image("resources/EriAnimations/Run/RunFoot15.png", false, Image.FILTER_NEAREST).getScaledCopy(3),
					new Image("resources/EriAnimations/Run/RunFoot16.png", false, Image.FILTER_NEAREST).getScaledCopy(3) };

			eriShoot = new Image[] { new Image("resources/EriAnimations/Shoot/Shoot1.png", false, Image.FILTER_NEAREST).getScaledCopy(3),
					new Image("resources/EriAnimations/Shoot/Shoot2.png", false, Image.FILTER_NEAREST).getScaledCopy(3),
					new Image("resources/EriAnimations/Shoot/Shoot3.png", false, Image.FILTER_NEAREST).getScaledCopy(3),
					new Image("resources/EriAnimations/Shoot/Shoot4.png", false, Image.FILTER_NEAREST).getScaledCopy(3),
					new Image("resources/EriAnimations/Shoot/Shoot5.png", false, Image.FILTER_NEAREST).getScaledCopy(3),
					new Image("resources/EriAnimations/Shoot/Shoot6.png", false, Image.FILTER_NEAREST).getScaledCopy(3) };
			
			eriJumpFoot1 = new Image[] { new Image("resources/EriAnimations/Jump1/JumpFoot1.png", false, Image.FILTER_NEAREST).getScaledCopy(3),
					new Image("resources/EriAnimations/Jump1/JumpFoot2.png", false, Image.FILTER_NEAREST).getScaledCopy(3),
					new Image("resources/EriAnimations/Jump1/JumpFoot3.png", false, Image.FILTER_NEAREST).getScaledCopy(3),
					new Image("resources/EriAnimations/Jump1/JumpFoot4.png", false, Image.FILTER_NEAREST).getScaledCopy(3),
					new Image("resources/EriAnimations/Jump1/JumpFoot5.png", false, Image.FILTER_NEAREST).getScaledCopy(3),
					new Image("resources/EriAnimations/Jump1/JumpFoot6.png", false, Image.FILTER_NEAREST).getScaledCopy(3) };
			
			eriJumpFoot2 = new Image[] { new Image("resources/EriAnimations/Jump2/JumpFoot1.png", false, Image.FILTER_NEAREST).getScaledCopy(3),
					new Image("resources/EriAnimations/Jump2/JumpFoot2.png", false, Image.FILTER_NEAREST).getScaledCopy(3),
					new Image("resources/EriAnimations/Jump2/JumpFoot3.png", false, Image.FILTER_NEAREST).getScaledCopy(3),
					new Image("resources/EriAnimations/Jump2/JumpFoot4.png", false, Image.FILTER_NEAREST).getScaledCopy(3),
					new Image("resources/EriAnimations/Jump2/JumpFoot5.png", false, Image.FILTER_NEAREST).getScaledCopy(3),
					new Image("resources/EriAnimations/Jump2/JumpFoot6.png", false, Image.FILTER_NEAREST).getScaledCopy(3) };
			
			eriJumpHead1 = new Image[] { new Image("resources/EriAnimations/Jump1/JumpHead1.png", false, Image.FILTER_NEAREST).getScaledCopy(3),
					new Image("resources/EriAnimations/Jump1/JumpHead2.png", false, Image.FILTER_NEAREST).getScaledCopy(3),
					new Image("resources/EriAnimations/Jump1/JumpHead3.png", false, Image.FILTER_NEAREST).getScaledCopy(3),
					new Image("resources/EriAnimations/Jump1/JumpHead4.png", false, Image.FILTER_NEAREST).getScaledCopy(3),
					new Image("resources/EriAnimations/Jump1/JumpHead5.png", false, Image.FILTER_NEAREST).getScaledCopy(3),
					new Image("resources/EriAnimations/Jump1/JumpHead6.png", false, Image.FILTER_NEAREST).getScaledCopy(3) };
			
			eriJumpHead2 = new Image[] { new Image("resources/EriAnimations/Jump2/JumpHead1.png", false, Image.FILTER_NEAREST).getScaledCopy(3),
					new Image("resources/EriAnimations/Jump2/JumpHead2.png", false, Image.FILTER_NEAREST).getScaledCopy(3),
					new Image("resources/EriAnimations/Jump2/JumpHead3.png", false, Image.FILTER_NEAREST).getScaledCopy(3),
					new Image("resources/EriAnimations/Jump2/JumpHead4.png", false, Image.FILTER_NEAREST).getScaledCopy(3),
					new Image("resources/EriAnimations/Jump2/JumpHead5.png", false, Image.FILTER_NEAREST).getScaledCopy(3),
					new Image("resources/EriAnimations/Jump1/JumpHead6.png", false, Image.FILTER_NEAREST).getScaledCopy(3) };
			
			soldierWalk = new Image[] { new Image("resources/SoldierAnimation/Run/SoldierRun1.png", false, Image.FILTER_NEAREST).getScaledCopy(3),
					new Image("resources/SoldierAnimation/Run/SoldierRun2.png", false, Image.FILTER_NEAREST).getScaledCopy(3),
					new Image("resources/SoldierAnimation/Run/SoldierRun3.png", false, Image.FILTER_NEAREST).getScaledCopy(3),
					new Image("resources/SoldierAnimation/Run/SoldierRun4.png", false, Image.FILTER_NEAREST).getScaledCopy(3),
					new Image("resources/SoldierAnimation/Run/SoldierRun5.png", false, Image.FILTER_NEAREST).getScaledCopy(3),
					new Image("resources/SoldierAnimation/Run/SoldierRun6.png", false, Image.FILTER_NEAREST).getScaledCopy(3),
					new Image("resources/SoldierAnimation/Run/SoldierRun7.png", false, Image.FILTER_NEAREST).getScaledCopy(3),
					new Image("resources/SoldierAnimation/Run/SoldierRun8.png", false, Image.FILTER_NEAREST).getScaledCopy(3),
					new Image("resources/SoldierAnimation/Run/SoldierRun9.png", false, Image.FILTER_NEAREST).getScaledCopy(3),
					new Image("resources/SoldierAnimation/Run/SoldierRun10.png", false, Image.FILTER_NEAREST).getScaledCopy(3),
					new Image("resources/SoldierAnimation/Run/SoldierRun11.png", false, Image.FILTER_NEAREST).getScaledCopy(3),
					new Image("resources/SoldierAnimation/Run/SoldierRun12.png", false, Image.FILTER_NEAREST).getScaledCopy(3),		
			};
			
			soldierShoot = new Image[] { new Image("resources/SoldierAnimation/Shoot/SoldierPreShoot1.png", false, Image.FILTER_NEAREST).getScaledCopy(3),
					new Image("resources/SoldierAnimation/Shoot/SoldierPreShoot2.png", false, Image.FILTER_NEAREST).getScaledCopy(3),
					new Image("resources/SoldierAnimation/Shoot/SoldierPreShoot3.png", false, Image.FILTER_NEAREST).getScaledCopy(3),
			};


		} catch (SlickException e) {
		}
	}

}
