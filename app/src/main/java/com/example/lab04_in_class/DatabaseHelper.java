package com.example.lab04_in_class;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DatabaseHelper extends SQLiteOpenHelper {

    public static final String DATABASE_NAME = "dictionary.db";
    public static final int DATABASE_VERSION = 1;

    public static final String TABLE_WORDS = "words";
    public static final String COLUMN_ID = "_id";
    public static final String COLUMN_WORD = "word";
    public static final String COLUMN_DEFINITION = "definition";

    private static final String CREATE_TABLE =
            "CREATE TABLE " + TABLE_WORDS + " (" +
                    COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                    COLUMN_WORD + " TEXT NOT NULL, " +
                    COLUMN_DEFINITION + " TEXT NOT NULL);";

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_TABLE);
        seedData(db);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_WORDS);
        onCreate(db);
    }

    // Populate the database with sample words
    private void seedData(SQLiteDatabase db) {
        String[][] words = {
                {"abandon", "To give up or leave behind; to desert."},
                {"abstract", "Existing in thought or as an idea but not having a physical or concrete existence."},
                {"accurate", "Free from errors or mistakes; correct in all details."},
                {"achieve", "To successfully bring about or reach a desired objective by effort, skill, or courage."},
                {"acquire", "To buy or obtain an object or asset for oneself."},
                {"adapt", "To make something suitable for a new use or purpose; modify."},
                {"adequate", "Satisfactory or acceptable in quality or quantity."},
                {"admire", "To regard with respect, warm approval, or satisfaction."},
                {"adventure", "An unusual and exciting, typically hazardous, experience or activity."},
                {"advocate", "A person who publicly supports or recommends a particular cause or policy."},
                {"algorithm", "A process or set of rules to be followed in calculations or problem-solving operations."},
                {"altitude", "The height of an object or point in relation to sea level or ground level."},
                {"ambiguous", "Open to more than one interpretation; not having one obvious meaning."},
                {"ambitious", "Having or showing a strong desire and determination to succeed."},
                {"analyze", "Examine methodically and in detail the constitution or structure of something."},
                {"ancient", "Belonging to the very distant past and no longer in existence."},
                {"anxiety", "A feeling of worry, nervousness, or unease about something with an uncertain outcome."},
                {"apparent", "Clearly visible or understood; obvious."},
                {"appreciate", "Recognize the full worth of; be grateful for."},
                {"approach", "Come near or nearer to someone or something in distance or time."},
                {"benefit", "An advantage or profit gained from something."},
                {"brilliant", "Exceptionally clever or talented; very bright or vivid."},
                {"capable", "Having the ability, fitness, or quality necessary to do or achieve a specified thing."},
                {"challenge", "A task or situation that tests someone's abilities."},
                {"collaborate", "Work jointly on an activity, especially to produce or create something."},
                {"complex", "Consisting of many different and connected parts; not easy to analyze or understand."},
                {"confidence", "The feeling or belief that one can rely on someone or something; firm trust."},
                {"consistent", "Acting or done in the same way over time, especially so as to be fair or accurate."},
                {"creative", "Relating to or involving the imagination or original ideas."},
                {"culture", "The arts and other manifestations of human intellectual achievement regarded collectively."},
                {"database", "A structured set of data held in a computer, especially one that is accessible in various ways."},
                {"decision", "A conclusion or resolution reached after consideration."},
                {"determine", "Cause something to occur in a particular way; be the decisive factor in."},
                {"develop", "Grow or cause to grow and become more mature, advanced, or elaborate."},
                {"diverse", "Showing a great deal of variety; very different."},
                {"economy", "The wealth and resources of a country or region in terms of production and consumption."},
                {"effective", "Successful in producing a desired or intended result."},
                {"efficient", "Achieving maximum productivity with minimum wasted effort or expense."},
                {"elaborate", "Involving many carefully arranged parts or details; detailed and complicated."},
                {"emerge", "Move out of or away from something and become visible."},
                {"encourage", "Give support, confidence, or hope to someone."},
                {"enormous", "Very large in size, quantity, or extent."},
                {"enthusiasm", "Intense and eager enjoyment, interest, or approval."},
                {"environment", "The surroundings or conditions in which a person, animal, or plant lives or operates."},
                {"evaluate", "Form an idea of the amount, number, or value of; assess."},
                {"evident", "Plain or obvious; clearly seen or understood."},
                {"evolution", "The process by which different kinds of living organisms are thought to have developed."},
                {"examine", "Inspect someone or something in detail to determine its nature or condition."},
                {"experiment", "A scientific procedure undertaken to make a discovery, test a hypothesis, or demonstrate a fact."},
                {"flexible", "Capable of bending easily without breaking; ready and able to change."},
                {"fundamental", "Forming a necessary base or core; of central importance."},
                {"generate", "Cause something to arise or come about; produce or create."},
                {"genuine", "Truly what something is said to be; authentic."},
                {"global", "Relating to the whole world; worldwide."},
                {"grateful", "Feeling or showing an appreciation of kindness; thankful."},
                {"harmony", "The combination of simultaneously sounded musical notes to produce a pleasing effect."},
                {"hypothesis", "A supposition or proposed explanation made on the basis of limited evidence."},
                {"identify", "Establish or indicate who or what someone or something is."},
                {"illuminate", "Light up; make something clearer and easier to understand."},
                {"implement", "Put a decision, plan, agreement, etc. into effect."},
                {"improve", "Make or become better."},
                {"innovation", "A new method, idea, product, etc.; the action or process of innovating."},
                {"integrity", "The quality of being honest and having strong moral principles."},
                {"intelligence", "The ability to acquire and apply knowledge and skills."},
                {"investigate", "Carry out a systematic or formal inquiry to discover and examine the facts."},
                {"knowledge", "Facts, information, and skills acquired by a person through experience or education."},
                {"language", "The method of human communication, either spoken or written."},
                {"logical", "Of or according to the rules of logic or formal argument."},
                {"magnificent", "Impressively beautiful, elaborate, or extravagant; striking."},
                {"manage", "Be in charge of; administer; run."},
                {"memory", "The faculty by which the mind stores and remembers information."},
                {"motivation", "The reason or reasons one has for acting or behaving in a particular way."},
                {"navigate", "Plan and direct the route or course of a ship, aircraft, or other form of transport."},
                {"objective", "Not influenced by personal feelings or opinions in considering and representing facts."},
                {"observe", "Notice or perceive something and register it as being significant."},
                {"opportunity", "A time or set of circumstances that makes it possible to do something."},
                {"organize", "Arrange into a structured whole; order."},
                {"perspective", "A particular attitude toward or way of regarding something; a point of view."},
                {"philosophy", "The study of the fundamental nature of knowledge, reality, and existence."},
                {"potential", "Latent qualities or abilities that may be developed and lead to future success."},
                {"practice", "The actual application or use of an idea, belief, or method."},
                {"principle", "A fundamental truth or proposition that serves as the foundation for a system of belief."},
                {"priority", "A thing that is regarded as more important than another."},
                {"problem", "A matter or situation regarded as unwelcome or harmful and needing to be dealt with."},
                {"process", "A series of actions or steps taken in order to achieve a particular end."},
                {"productive", "Achieving or producing a significant amount or result."},
                {"profound", "Very great or intense; having or showing great knowledge or insight."},
                {"progress", "Forward or onward movement toward a destination; development toward an improved condition."},
                {"quality", "The standard of something as measured against other things of a similar kind."},
                {"recognize", "Identify someone or something from having encountered them before."},
                {"reliable", "Consistently good in quality or performance; able to be trusted."},
                {"remarkable", "Worthy of attention; striking."},
                {"represent", "Be entitled or appointed to act or speak for someone."},
                {"research", "The systematic investigation into and study of materials and sources in order to establish facts."},
                {"resolve", "Settle or find a solution to a problem, dispute, or contentious matter."},
                {"resource", "A stock or supply of money, materials, staff, and other assets that can be drawn on."},
                {"responsible", "Having an obligation to do something, or having control over or care for someone."},
                {"significant", "Sufficiently great or important to be worthy of attention; noteworthy."},
                {"solution", "A means of solving a problem or dealing with a difficult situation."},
                {"strategy", "A plan of action designed to achieve a long-term or overall aim."},
                {"structure", "The arrangement of and relations between the parts or elements of something complex."},
                {"success", "The accomplishment of an aim or purpose."},
                {"sufficient", "Enough; adequate."},
                {"support", "Bear all or part of the weight of; hold up."},
                {"technology", "The application of scientific knowledge for practical purposes."},
                {"theory", "A supposition or a system of ideas intended to explain something."},
                {"transform", "Make a thorough or dramatic change in the form, appearance, or character of."},
                {"understand", "Perceive the intended meaning of words, a language, or a speaker."},
                {"unique", "Being the only one of its kind; unlike anything else."},
                {"valuable", "Worth a great deal of money; extremely useful or important."},
                {"versatile", "Able to adapt or be adapted to many different functions or activities."},
                {"verisimilitude", "The appearance of being true or real; plausibility."},
                {"vision", "The faculty or state of being able to see; the ability to think about the future with imagination."},
                {"wisdom", "The quality of having experience, knowledge, and good judgment."}
        };

        for (String[] entry : words) {
            db.execSQL("INSERT INTO " + TABLE_WORDS +
                            " (" + COLUMN_WORD + ", " + COLUMN_DEFINITION + ") VALUES (?, ?)",
                    new String[]{entry[0], entry[1]});
        }
    }
}