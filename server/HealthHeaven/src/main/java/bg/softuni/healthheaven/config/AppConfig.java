package bg.softuni.healthheaven.config;

import bg.softuni.healthheaven.model.entities.Article;
import bg.softuni.healthheaven.model.entities.Doctor;
import bg.softuni.healthheaven.model.entities.Item;
import bg.softuni.healthheaven.model.entities.User;
import bg.softuni.healthheaven.model.enums.RoleEnum;
import bg.softuni.healthheaven.repositories.ArticleRepository;
import bg.softuni.healthheaven.repositories.DoctorRepository;
import bg.softuni.healthheaven.repositories.ItemRepository;
import bg.softuni.healthheaven.repositories.UserRepository;
import com.google.gson.Gson;
import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.List;


@Configuration
public class AppConfig {

    @Bean
    public ModelMapper modelMapper() {
        return new ModelMapper();
    }

    @Bean
    public Gson gson() {
        return new Gson();
    }

    @Bean
    public User dataSourceInitializer(UserRepository userRepository,
                                      PasswordEncoder passwordEncoder,
                                      ItemRepository itemRepository,
                                      ArticleRepository articleRepository,
                                      DoctorRepository doctorRepository) {

//        For seed data in application use this if statement
//        Run Tests with Coverage without this if statement

//        if (userRepository.count() == 0) {
//            User user = new User();
//            user.setFirstName("Admin");
//            user.setLastName("Admin");
//            user.setEmail("admin@gmail.com");
//            user.setRole(RoleEnum.ADMIN);
//            user.setPassword(passwordEncoder.encode("admin"));
//            userRepository.save(user);
//
//            Item item1 = new Item();
//            item1.setName("Vitamin C Supplement");
//            item1.setPrice(20.00);
//            item1.setDescription("High-quality vitamin C supplement to boost immunity. This supplement provides the necessary daily dose of vitamin C, which is essential for the growth, development, and repair of all body tissues. It is involved in many body functions, including the formation of collagen, absorption of iron, proper functioning of the immune system, wound healing, and the maintenance of cartilage, bones, and teeth. Regular intake can help protect against immune system deficiencies.");
//            item1.setImageURL("https://www.health.com/thmb/X7x_mOg1fiZAR24xx5QfYP4fce0=/fit-in/1500x1000/filters:no_upscale():max_bytes(150000):strip_icc()/nature-made-vitamin-c-500-mg-d16021baf05b47dc94433c65ab590da7.jpg");
//
//            Item item2 = new Item();
//            item2.setName("Multivitamin Tablets");
//            item2.setPrice(30.00);
//            item2.setDescription("Comprehensive multivitamin for daily nutritional needs. These tablets are designed to fill nutritional gaps and ensure that you get your daily recommended intake of essential vitamins and minerals. They support overall health, boost energy levels, enhance immune function, and promote healthy skin, hair, and nails. With a balanced formula, these multivitamins help you maintain a healthy lifestyle, making sure your body gets the nutrients it needs to function optimally every day.");
//            item2.setImageURL("https://m.media-amazon.com/images/I/51oQrGnRdTL.jpg");
//
//            Item item3 = new Item();
//            item3.setName("Pain Relief Tablets");
//            item3.setPrice(15.00);
//            item3.setDescription("Effective pain relief tablets for headaches and muscle pain. These tablets provide fast and effective relief from various types of pain, including headaches, muscle aches, back pain, and arthritis. Formulated with powerful painkillers, they work quickly to alleviate discomfort and help you get back to your daily activities. Whether you're dealing with chronic pain or occasional aches, these tablets are a reliable solution to help manage your symptoms and improve your quality of life.");
//            item3.setImageURL("https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcR1DzmeuHx8ii4naUzhxpEBIGsN5-AM4fj44w&s");
//
//            Item item4 = new Item();
//            item4.setName("Cold and Flu Tablets");
//            item4.setPrice(18.00);
//            item4.setDescription("Tablets to alleviate cold and flu symptoms. These tablets are specially formulated to provide relief from the common symptoms of cold and flu, including fever, chills, body aches, sore throat, and congestion. They contain a blend of ingredients that work together to reduce fever, ease pain, and clear nasal congestion, helping you recover faster and feel better sooner. Ideal for keeping on hand during the cold and flu season to manage symptoms effectively.");
//            item4.setImageURL("https://phelans.ie/cdn/shop/products/AdvilCold_FluTablets20Pack_800x.jpg?v=1604490858");
//
//            Item item5 = new Item();
//            item5.setName("Antacid Tablets");
//            item5.setPrice(12.00);
//            item5.setDescription("Quick relief from heartburn and indigestion. These antacid tablets provide fast-acting relief from the discomfort of heartburn, acid indigestion, and upset stomach. Formulated to neutralize stomach acid on contact, they help to quickly alleviate the burning sensation associated with acid reflux. Whether you've overindulged or are dealing with chronic digestive issues, these tablets can help restore comfort and allow you to enjoy your meals without worry.");
//            item5.setImageURL("https://i5.walmartimages.com/asr/455a601c-d69f-4046-9e6b-55d29fcefc43_1.8353eff8576b9a758e5af63f6c2696f9.jpeg");
//
//            Item item6 = new Item();
//            item6.setName("Fish Oil Supplements");
//            item6.setPrice(22.00);
//            item6.setDescription("Omega-3 rich fish oil supplements for heart health. These supplements are packed with omega-3 fatty acids, which are essential for maintaining cardiovascular health. Regular intake can help reduce inflammation, lower blood pressure, decrease triglycerides, and reduce the risk of heart disease. Additionally, omega-3s support brain health, joint function, and overall well-being. These fish oil supplements are a convenient way to ensure you get enough of these crucial nutrients.");
//            item6.setImageURL("https://m.media-amazon.com/images/I/71m62G2Sr3L.jpg");
//
//            Item item7 = new Item();
//            item7.setName("Probiotics");
//            item7.setPrice(28.00);
//            item7.setDescription("Probiotic capsules for digestive health. These probiotics contain live beneficial bacteria that support a healthy gut microbiome. Regular use can help improve digestion, enhance nutrient absorption, boost immune function, and maintain overall gastrointestinal health. Ideal for individuals dealing with digestive issues such as bloating, gas, and irregularity, these probiotic capsules are an excellent addition to your daily health regimen.");
//            item7.setImageURL("https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcSDfsgem4E4K7WGIOW_PA9_sspBfVEDWcXpHg&s");
//
//            Item item8 = new Item();
//            item8.setName("Cough Syrup");
//            item8.setPrice(14.00);
//            item8.setDescription("Soothing syrup for cough relief. This cough syrup is formulated to provide effective relief from persistent coughs. It contains ingredients that help to soothe the throat, reduce irritation, and suppress the cough reflex. Whether you're dealing with a dry, tickly cough or a chesty, productive cough, this syrup can help ease your symptoms and provide much-needed comfort. Keep it on hand during cold and flu season for fast relief.");
//            item8.setImageURL("https://5.imimg.com/data5/SELLER/Default/2023/8/338004786/LD/SN/AI/125278182/dry-cough-syrup-newcuf-dx-cough-syrup-500x500.jpeg");
//
//            Item item9 = new Item();
//            item9.setName("Sleep Aids");
//            item9.setPrice(20.00);
//            item9.setDescription("Tablets to help improve sleep quality. These sleep aids are designed to help you fall asleep faster and stay asleep longer. They contain natural and effective ingredients that promote relaxation and support a healthy sleep cycle. Whether you're dealing with occasional sleeplessness or chronic insomnia, these tablets can help you achieve restful, rejuvenating sleep, so you wake up feeling refreshed and ready to take on the day.");
//            item9.setImageURL("https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcTY3MDlGCLuF1j-lyQG0FmdXbxn_NUIkVu5FA&s");
//
//
//            itemRepository.saveAll(List.of(item1, item2, item3, item4, item5, item6, item7, item8, item9));
//
//
//
//            Article article1 = new Article();
//            article1.setTitle("Предпазване от диабет: Как да поддържаме здравословен начин на живот");
//            article1.setContent("Диабетът е хронично заболяване, което засяга милиони хора по света. Съществуват два основни типа диабет: тип 1 и тип 2. Диабет тип 1 е автоимунно заболяване, което обикновено се диагностицира в детска възраст, докато диабет тип 2 се развива главно при възрастни и е свързан с начина на живот. Предпазването от диабет тип 2 е възможно чрез предприемане на здравословни навици и промени в начина на живот.\n" +
//                    "\n" +
//                    "1. Здравословно хранене\n" +
//                    "Избягвайте захар и преработени въглехидрати: Храните с високо съдържание на захар и преработени въглехидрати могат да доведат до повишаване на нивата на кръвната захар и инсулина, което може да увеличи риска от развитие на диабет. Избягвайте сладки напитки, сладкиши и бързи храни.\n" +
//                    "\n" +
//                    "Изберете пълнозърнести храни: Пълнозърнестите храни като овес, кафяв ориз и пълнозърнести макарони осигуряват по-стабилни нива на кръвната захар и помагат за поддържането на здравословно тегло.\n" +
//                    "\n" +
//                    "Консумирайте повече фибри: Фибрите помагат за регулирането на кръвната захар и намаляват риска от диабет. Храни богати на фибри включват плодове, зеленчуци, бобови растения и пълнозърнести храни.\n" +
//                    "\n" +
//                    "2. Поддържане на здравословно тегло\n" +
//                    "Излишното тегло, особено натрупването на мазнини около корема, е свързано с повишен риск от диабет тип 2. Поддържането на здравословно тегло чрез балансирана диета и редовна физическа активност е ключово за предпазване от диабет.\n" +
//                    "\n" +
//                    "3. Редовна физическа активност\n" +
//                    "Упражнения: Редовната физическа активност помага за контрола на теглото, понижава кръвната захар и повишава чувствителността към инсулина. Стремете се към поне 150 минути умерено интензивни упражнения като ходене, плуване или колоездене седмично.\n" +
//                    "\n" +
//                    "Силови тренировки: Упражненията с тежести или съпротивителни тренировки също са полезни за поддържане на мускулната маса и подобряване на метаболизма.\n" +
//                    "\n" +
//                    "4. Ограничаване на алкохола и отказ от тютюнопушене\n" +
//                    "Алкохол: Консумацията на алкохол трябва да бъде в умерени количества, тъй като прекомерната употреба може да повиши риска от диабет. Препоръчва се да се ограничите до една напитка на ден за жените и две напитки на ден за мъжете.\n" +
//                    "\n" +
//                    "Тютюнопушене: Пушенето увеличава риска от различни хронични заболявания, включително диабет. Отказът от тютюнопушене е една от най-важните стъпки за подобряване на общото здраве и предпазване от диабет.\n" +
//                    "\n" +
//                    "5. Редовни медицински прегледи\n" +
//                    "Редовните прегледи при лекаря помагат за ранното откриване на признаци на диабет и други свързани заболявания. Проверявайте редовно нивата на кръвната захар, кръвното налягане и холестерола.\n" +
//                    "\n" +
//                    "Заключение\n" +
//                    "Предпазването от диабет тип 2 е възможно чрез здравословен начин на живот. Бъдете активни, хранете се правилно, поддържайте здравословно тегло и избягвайте вредните навици като пушене и прекомерна консумация на алкохол. Редовните медицински прегледи също са важни за ранното откриване и управление на рисковите фактори. С правилния подход можем да намалим значително риска от диабет и да подобрим качеството си на живот.");
//            article1.setAuthor(user);
//            article1.setTimeOnPost(Instant.now());
//            article1.setImageURL("https://skener.news/wp-content/uploads/2016/10/maxresdefault-1.jpg");
//            articleRepository.save(article1);
//
//            Article article2 = new Article();
//            article2.setTitle("Ползите от медитацията");
//            article2.setContent("Медитацията е древна практика, която предлага множество ползи за физическото и психическото здраве. Тя може да помогне за намаляване на стреса, подобряване на концентрацията и повишаване на общото благополучие.\n" +
//                    "\n" +
//                    "1. Намаляване на стреса\n" +
//                    "Медитацията помага за отпускане на ума и тялото, което води до намаляване на нивата на стрес. Редовната практика може да подобри реакцията на организма към стресови ситуации.\n" +
//                    "\n" +
//                    "2. Подобряване на концентрацията\n" +
//                    "Медитацията тренира ума да бъде по-фокусиран и внимателен. Това може да подобри способността за концентрация и изпълнение на задачи.\n" +
//                    "\n" +
//                    "3. Подобряване на съня\n" +
//                    "Медитацията може да помогне за подобряване на качеството на съня чрез отпускане на ума и намаляване на безпокойството.\n" +
//                    "\n" +
//                    "4. Повишаване на емоционалното благополучие\n" +
//                    "Редовната медитация може да помогне за повишаване на емоционалното благополучие чрез намаляване на негативните емоции и увеличаване на позитивните.\n" +
//                    "\n" +
//                    "5. Подобряване на физическото здраве\n" +
//                    "Медитацията може да има положителен ефект върху физическото здраве, включително понижаване на кръвното налягане и укрепване на имунната система.\n" +
//                    "\n" +
//                    "Заключение\n" +
//                    "Медитацията е полезна практика, която може да подобри както физическото, така и психическото здраве. Редовната медитация може да донесе множество ползи и да подобри качеството на живот.");
//            article2.setAuthor(user);
//            article2.setTimeOnPost(Instant.now());
//            article2.setImageURL("https://2sport4life.com/wp-content/uploads/2021/11/Untitled-design-1.png");
//            articleRepository.save(article2);
//
//            Article article3 = new Article();
//            article3.setTitle("Значението на физическата активност");
//            article3.setContent("Физическата активност е ключов елемент за поддържането на здраво тяло и дух. Редовното упражнение помага за предотвратяване на множество хронични заболявания и подобрява общото благополучие.\n" +
//                    "\n" +
//                    "1. Подобряване на сърдечно-съдовото здраве\n" +
//                    "Редовната физическа активност укрепва сърцето и подобрява кръвообращението, което може да намали риска от сърдечни заболявания.\n" +
//                    "\n" +
//                    "2. Управление на теглото\n" +
//                    "Физическата активност помага за поддържане на здравословно тегло чрез изгаряне на калории и изграждане на мускулна маса.\n" +
//                    "\n" +
//                    "3. Подобряване на настроението\n" +
//                    "Упражненията стимулират производството на ендорфини, които подобряват настроението и намаляват стреса и тревожността.\n" +
//                    "\n" +
//                    "4. Повишаване на енергията\n" +
//                    "Физическата активност може да повиши нивата на енергия и да подобри цялостната издръжливост.\n" +
//                    "\n" +
//                    "5. Подобряване на костите и мускулите\n" +
//                    "Редовните упражнения укрепват костите и мускулите, което може да предотврати остеопороза и други мускулно-скелетни проблеми.\n" +
//                    "\n" +
//                    "Заключение\n" +
//                    "Физическата активност е важна за поддържане на добро здраве и благополучие. Редовните упражнения могат да подобрят физическото и психическото здраве и да допринесат за по-дълъг и здравословен живот.");
//            article3.setAuthor(user);
//            article3.setTimeOnPost(Instant.now());
//            article3.setImageURL("https://lh6.googleusercontent.com/proxy/-0odMfZXUZvNAioudbjfUXKvLPOycBkLFS9wHruG-ZFjLbzUc0Yg863d4Rdxdm9RB7XHIvaIAFwrGFDhBK3cZCvLqX6WdoQ6KRlyISFIlULzUCQ");
//            articleRepository.save(article3);
//
//            Article article4 = new Article();
//            article4.setTitle("Балансирано хранене");
//            article4.setContent("Балансираното хранене е основен елемент за поддържане на здравето и жизнеността. То включва консумацията на различни видове храни в правилни пропорции и осигурява необходимите хранителни вещества на организма.\n" +
//                    "\n" +
//                    "1. Консумация на разнообразни храни\n" +
//                    "Важно е да консумираме разнообразни храни, включително плодове, зеленчуци, пълнозърнести храни, протеини и здравословни мазнини, за да осигурим всички необходими хранителни вещества.\n" +
//                    "\n" +
//                    "2. Ограничаване на захарта и солта\n" +
//                    "Прекомерната консумация на захар и сол може да доведе до различни здравословни проблеми като затлъстяване и високо кръвно налягане. Опитайте се да намалите количеството захар и сол в храната си.\n" +
//                    "\n" +
//                    "3. Прием на достатъчно вода\n" +
//                    "Водата е жизненоважна за поддържането на хидратацията и правилното функциониране на организма. Пийте достатъчно вода всеки ден.\n" +
//                    "\n" +
//                    "4. Балансиране на калориите\n" +
//                    "Балансирайте калориите, които консумирате, с тези, които изгаряте чрез физическа активност. Това ще помогне за поддържане на здравословно тегло.\n" +
//                    "\n" +
//                    "5. Избягване на преработени храни\n" +
//                    "Преработените храни често съдържат високи нива на захар, сол и нездравословни мазнини. Опитайте се да консумирате повече пресни и натурални храни.\n" +
//                    "\n" +
//                    "Заключение\n" +
//                    "Балансираното хранене е ключът към здравословен начин на живот. Осигурете си разнообразна и балансирана диета, за да поддържате доброто си здраве и енергия.");
//            article4.setAuthor(user);
//            article4.setTimeOnPost((LocalDateTime.of(2024, 8, 7, 10, 0).toInstant(ZoneOffset.UTC)));
//            article4.setImageURL("https://cache2.24chasa.bg/Images/Cache/364/IMAGE_14821364_40_0.jpg");
//            articleRepository.save(article4);
//
//            Article article5 = new Article();
//            article5.setTitle("Управление на стреса");
//            article5.setContent("Стресът е неизбежна част от живота, но е важно да знаем как да го управляваме, за да избегнем негативните му последици върху здравето. Ефективното управление на стреса може да подобри качеството на живот и общото благополучие.\n" +
//                    "\n" +
//                    "1. Разпознаване на стресовите фактори\n" +
//                    "Първата стъпка към управлението на стреса е разпознаването на факторите, които го предизвикват. Това може да бъдат работа, лични отношения или финансови проблеми.\n" +
//                    "\n" +
//                    "2. Използване на техники за релаксация\n" +
//                    "Техники като дълбоко дишане, медитация и йога могат да помогнат за отпускане и намаляване на стреса.\n" +
//                    "\n" +
//                    "3. Редовна физическа активност\n" +
//                    "Упражненията са ефективен начин за освобождаване на напрежението и подобряване на настроението. Редовната физическа активност може да помогне за управление на стреса.\n" +
//                    "\n" +
//                    "4. Поддържане на здравословен начин на живот\n" +
//                    "Балансираното хранене, достатъчният сън и избягването на прекомерна консумация на алкохол и кофеин могат да помогнат за намаляване на стреса.\n" +
//                    "\n" +
//                    "5. Социална подкрепа\n" +
//                    "Подкрепата от семейството и приятелите е важна за управлението на стреса. Споделянето на проблемите с близките може да помогне за намаляване на напрежението.\n" +
//                    "\n" +
//                    "Заключение\n" +
//                    "Ефективното управление на стреса е ключът към поддържането на добро физическо и психическо здраве. Използвайте различни техники за релаксация, поддържайте здравословен начин на живот и търсете подкрепа от близките, за да се справите със стреса.");
//            article5.setAuthor(user);
//            article5.setTimeOnPost(LocalDateTime.of(2024, 3, 7, 10, 0).toInstant(ZoneOffset.UTC));
//            article5.setImageURL("https://obuchi.me/wp-content/uploads/2023/07/upravlenie-na-stresa-1.png");
//            articleRepository.save(article5);
//
//            Article article6 = new Article();
//            article6.setTitle("Важността на съня");
//            article6.setContent("Сънят е от съществено значение за поддържането на добро здраве и благополучие. Качественият сън помага за възстановяването на организма, подобряването на умствената функция и укрепването на имунната система.\n" +
//                    "\n" +
//                    "1. Подобряване на когнитивните функции\n" +
//                    "Добрият сън е от съществено значение за паметта, концентрацията и обучението. Липсата на сън може да доведе до намалена умствена функция и затруднения в работата и ученето.\n" +
//                    "\n" +
//                    "2. Поддържане на физическото здраве\n" +
//                    "Сънят играе важна роля за поддържането на здраво тяло. Той подпомага възстановяването на мускулите, регулирането на метаболизма и поддържането на здравословно тегло.\n" +
//                    "\n" +
//                    "3. Укрепване на имунната система\n" +
//                    "Качественият сън е важен за функционирането на имунната система. Липсата на сън може да отслаби имунитета и да увеличи риска от инфекции и заболявания.\n" +
//                    "\n" +
//                    "4. Подобряване на настроението\n" +
//                    "Добрият сън е свързан с по-добро настроение и по-малко стрес и тревожност. Недостатъчният сън може да доведе до раздразнителност и депресия.\n" +
//                    "\n" +
//                    "5. Предотвратяване на хронични заболявания\n" +
//                    "Редовният и качествен сън може да помогне за предотвратяване на различни хронични заболявания като диабет, сърдечни заболявания и високо кръвно налягане.\n" +
//                    "\n" +
//                    "Заключение\n" +
//                    "Сънят е важен за поддържането на добро здраве и благополучие. Осигурете си достатъчно сън всяка нощ, за да подобрите физическото и психическото си здраве и да се почувствате по-енергични и здрави.");
//            article6.setAuthor(user);
//            article6.setTimeOnPost(LocalDateTime.of(2023, 8, 7, 10, 0).toInstant(ZoneOffset.UTC));
//            article6.setImageURL("https://www.tialoto.bg/media/files/resized/article/615x348/197/21496875bcd10b81a9d2325c567fe197-pexels-photo-935777.jpeg");
//            articleRepository.save(article6);
//
//            Doctor doctor1 = new Doctor();
//            doctor1.setName("Dr. John Doe");
//            doctor1.setSpecialization("Cardiologist");
//            doctor1.setPhoneNumber("0888123456");
//            doctor1.setProfilePictureURL("https://www.anadolumedicalcenter.bg/wp-content/uploads/2014/04/ayhan-erdemir-1.jpg");
//            doctor1.setDescription("Dr. John Doe is a cardiology specialist with over 15 years of experience. He has been instrumental in developing advanced treatment protocols for heart diseases. Dr. Doe has published numerous research papers on cardiovascular health and is a frequent speaker at international cardiology conferences. His approach to patient care is holistic, focusing not only on treating the disease but also on educating patients about heart-healthy lifestyles.");
//
//            Doctor doctor2 = new Doctor();
//            doctor2.setName("Dr. Jane Smith");
//            doctor2.setSpecialization("Dermatologist");
//            doctor2.setPhoneNumber("0888123457");
//            doctor2.setProfilePictureURL("https://cdn.pixabay.com/photo/2020/06/20/15/30/woman-doctor-5321351_1280.jpg");
//            doctor2.setDescription("Dr. Jane Smith is an experienced dermatologist specializing in the treatment of skin diseases. With over a decade of experience, Dr. Smith has become renowned for her expertise in managing chronic skin conditions like psoriasis and eczema. She has been involved in several clinical trials aimed at developing new dermatological therapies. Dr. Smith is dedicated to her patients' well-being, providing personalized care plans that address both medical and cosmetic concerns.");
//
//            Doctor doctor3 = new Doctor();
//            doctor3.setName("Dr. Emily Johnson");
//            doctor3.setSpecialization("Neurologist");
//            doctor3.setPhoneNumber("0888123458");
//            doctor3.setProfilePictureURL("https://www.anadolumedicalcenter.bg/wp-content/uploads/2014/04/ayse-sokullu-1.jpg");
//            doctor3.setDescription("Dr. Emily Johnson is a neurologist with a focus on treating neurological disorders. She has extensive experience in diagnosing and managing conditions such as multiple sclerosis, Parkinson's disease, and epilepsy. Dr. Johnson's research in neurodegenerative diseases has been published in prestigious medical journals. She is known for her compassionate approach to patient care, ensuring that patients and their families are well-informed and supported throughout their treatment journey.");
//
//            doctorRepository.save(doctor1);
//            doctorRepository.save(doctor2);
//            doctorRepository.save(doctor3);
//
//
//            return user;
//
//
//        }

        return null;
    }
}
