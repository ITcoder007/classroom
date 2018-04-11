package reflect;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class TestReflection {
	public static void main(String[] args) {
//		testSameOneClass();
//		testSynchronizedClass();
//		testGetHeroByClassName();
//		testGetHeroByConfig();
//		testGetField();
//		testGetMethod();
		testCase();
	}

	//从配置文件读取信息，用反射创建对象，调用方法
	private static void testCase() {
		File file = new File("/Users/gengjiaxing/git/classroom/src/reflect/hero.config");
		
			FileReader fileReader = null;
			try {
				fileReader = new FileReader(file);
				char[] all = new char[(int)file.length()];
				fileReader.read(all);
				String fileContent = new String(all);
				String[] str = fileContent.split("\n");
				String hero1ClassName = str[0];
				String hero1Name = str[1];
				String hero2ClassName = str[2]; 
				String hero2Name = str[3];
				
				//根据反射，获取hero1，并且给hero1的name字段赋值
				Object h1 = getHero(hero1ClassName,hero1Name);
				Object h2 = getHero(hero2ClassName,hero2Name);
				
				//1 攻击 2
				Method method = Class.forName(hero1ClassName).getMethod("attack", Hero.class);
				method.invoke(h1, h2);
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			} catch (NoSuchMethodException e) {
				e.printStackTrace();
			} catch (SecurityException e) {
				e.printStackTrace();
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			} catch (IllegalAccessException e) {
				e.printStackTrace();
			} catch (IllegalArgumentException e) {
				e.printStackTrace();
			} catch (InvocationTargetException e) {
				e.printStackTrace();
			}
	}

	private static Object getHero(String heroClassName, String heroName) {
		Object obj = new Object();
		try {
			Class<?> c = Class.forName(heroClassName);
			Constructor<?> constructor = c.getConstructor();
			obj = (Object)constructor.newInstance();
			Field field = c.getField("name");
			field.set(obj, heroName);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (NoSuchMethodException e) {
			e.printStackTrace();
		} catch (SecurityException e) {
			e.printStackTrace();
		} catch (InstantiationException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			e.printStackTrace();
		} catch (NoSuchFieldException e) {
			e.printStackTrace();
		}
		return obj;
	}

	//测试反射方式调用方法
	@SuppressWarnings("unused")
	private static void testGetMethod() {
		Hero hero = new Hero();
		hero.setName("h1");
		System.out.println(hero.getName());
		
		try {
			Method method = Hero.class.getMethod("setName", String.class);
			method.invoke(hero, "h2");
		} catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException | NoSuchMethodException | SecurityException e) {
			e.printStackTrace();
		}
		System.out.println(hero.getName());
	}

	//测试反射方式获得字段
	@SuppressWarnings("unused")
	private static void testGetField() {
		Hero h = new Hero();
		h.name = "h1";
		System.out.println(h.name);
		try {
			Field declaredField = Hero.class.getDeclaredField("name");
			declaredField.set(h, "h2");
		} catch (NoSuchFieldException | SecurityException | IllegalArgumentException | IllegalAccessException e) {
			e.printStackTrace();
		}
		System.out.println(h.name);
	}

	//基于配置的编程思想：源代码不需要发生任何变化，当配置文件改变时，就可以得到不同的对象，导致逻辑的改变；
	//Spring框架中的Ioc和DI底层就是基于这样的机制实现的
	@SuppressWarnings({ "resource", "rawtypes", "unchecked", "unused" })
	private static void testGetHeroByConfig() {
		File file = new File("/Users/gengjiaxing/git/classroom/src/reflect/Hero.config");
		String cName = "";
		try {
			FileReader fileReader = new FileReader(file);
			char[] all = new char[(int)file.length()];
			fileReader.read(all);
			cName = new String(all);
			Class c = Class.forName(cName);
			Constructor constructor = c.getConstructor();
			Hero h = (Hero) constructor.newInstance();
			System.out.println(h);
		} catch (FileNotFoundException e1) {
			e1.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (InstantiationException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			e.printStackTrace();
		} catch (NoSuchMethodException e) {
			e.printStackTrace();
		} catch (SecurityException e) {
			e.printStackTrace();
		}
	}

	//通过类名获取对象
	@SuppressWarnings({ "unused", "rawtypes", "unchecked" })
	private static void testGetHeroByClassName() {
		Hero h1 = new Hero();
		h1.name = "h1";
		System.out.println(h1);

		String cName = "reflect.Hero";
		try {
			Class c = Class.forName(cName);
			Constructor constructor = c.getConstructor();
			Hero h2 = (Hero)constructor.newInstance();
			h2.name = "h2";
			System.out.println(h2);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (NoSuchMethodException e) {
			e.printStackTrace();
		} catch (SecurityException e) {
			e.printStackTrace();
		} catch (InstantiationException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			e.printStackTrace();
		}
	}

	//测试静态方法加synchronized 等同于锁定类对象
	@SuppressWarnings("unused")
	private static void testSynchronizedClass() {
		// 线程1 调用synchronized修饰的静态方法
		Thread t1 = new Thread() {
			public void run() {
				method1();
			}
		};
		t1.setName("第一个线程");
		t1.start();
		
		// 线程2 调用synchronized修饰的class对象的方法
		Thread t2 = new Thread() {
			public void run() {
				method2();
			}
		};
		t2.setName("第二个线程");
		t2.start();
	}
	
	public static synchronized void method1() {
		System.out.println("进入 方式一");
		try {
			Thread.sleep(5000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	
	public static void method2() {
		synchronized(TestReflection.class) {
			System.out.println("进入 方式二");
			try {
				Thread.sleep(5000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}

	//测试一个虚拟机中，三种加载类对象方式加载的一样
	@SuppressWarnings({ "rawtypes", "unused" })
	private static void testSameOneClass() {
		try {
//			Class c1 = Class.forName("reflect.Hero");
			//该方式不会导致静态属性初始化
//			Class c2 = Hero.class;
			Class c3 = new Hero().getClass();
//			System.out.println(c1==c2);
//			System.out.println(c1==c3);
		} catch (Exception e) {
			e.printStackTrace();
		};

		
	}
}
